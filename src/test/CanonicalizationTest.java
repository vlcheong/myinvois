package com.einvoice.data;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.NodeIterator;
import org.xml.sax.SAXException;

import javax.xml.crypto.Data;
import javax.xml.crypto.NodeSetData;
import javax.xml.crypto.OctetStreamData;
import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.TransformException;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;

public class CanonicalizationTest {

    private static final String XML = """
            <?xml version="1.0" encoding="UTF-8"?>
            <PurchaseOrder xmlns:a="http://www.ietf.org" xmlns="http://www.example.com">
                <foo Id="1">bar &amp; beer</foo>
                <a:name/>
                <compute><![CDATA[value>"0" && value<"10" ?"valid":"error"]]></compute>
            </PurchaseOrder>
        """.trim();

    @Test
    public void run() throws Exception {
        String minified = minify(XML);
        System.out.println(minified);
        transform(minified);
    }

    private static final String XSLT = """
            <xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
                <xsl:output indent="no"/>
                <xsl:strip-space elements="*"/>
                <xsl:template match="@*|node()">
                    <xsl:copy>
                        <xsl:apply-templates select="@*|node()"/>
                    </xsl:copy>
                </xsl:template>
            </xsl:stylesheet>
        """;

    private String minify(String xml) throws TransformerException {
        Source xsltSource = new StreamSource(new StringReader(XSLT));
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(xsltSource);
        Source source = new StreamSource(new StringReader(xml));
        StringWriter writer = new StringWriter();
        Result result = new StreamResult(writer);
        transformer.transform(source, result);
        return writer.toString();
    }

    private Document toDocument(String xml) throws ParserConfigurationException, IOException, SAXException {
        byte[] bytes = xml.getBytes(StandardCharsets.UTF_8);
        try (ByteArrayInputStream input = new ByteArrayInputStream(bytes)) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder docBuilder = factory.newDocumentBuilder();
            return docBuilder.parse(input);
        }
    }

    private void transform(String xml) throws
        ParserConfigurationException, IOException, SAXException,
        InvalidAlgorithmParameterException, NoSuchAlgorithmException,
        TransformException {
        Document doc = toDocument(xml);
        Data data = new NodeSetDataImpl(doc, NodeSetDataImpl.getRootNodeFilter());

        XMLSignatureFactory signatureFactory = XMLSignatureFactory.getInstance("DOM");
        CanonicalizationMethod canonicalizationMethod = signatureFactory.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE_11, (C14NMethodParameterSpec) null);
        OctetStreamData streamData = (OctetStreamData) canonicalizationMethod.transform(data, null);
        System.out.println("mimeType: " + streamData.getMimeType());
        System.out.println("URI: " + streamData.getURI());
        String result = new String(streamData.getOctetStream().readAllBytes(), StandardCharsets.UTF_8);
        System.out.println(result);
    }

    static final class NodeSetDataImpl implements NodeSetData<Node>, Iterator<Node> {

        private Node ivNode;

        private NodeFilter ivNodeFilter;

        private DocumentTraversal ivDocumentTraversal;

        private NodeIterator ivNodeIterator;

        private Node ivNextNode;

        NodeSetDataImpl(Node pNode, NodeFilter pNodeFilter) {
            this.ivNode = pNode;
            this.ivNodeFilter = pNodeFilter;
            Document ivDocument;
            if (this.ivNode instanceof Document) {
                ivDocument = (Document) ivNode;
            } else {
                ivDocument = ivNode.getOwnerDocument();
            }
            this.ivDocumentTraversal = (DocumentTraversal) ivDocument;
        }

        private NodeSetDataImpl(NodeIterator pNodeIterator) {
            this.ivNodeIterator = pNodeIterator;
        }

        @Override
        public Iterator<Node> iterator() {
            NodeIterator nodeIterator = ivDocumentTraversal.createNodeIterator(
                ivNode, NodeFilter.SHOW_ALL, ivNodeFilter, false);
            return new NodeSetDataImpl(nodeIterator);
        }

        @Override
        public boolean hasNext() {
            return checkNextNode() != null;
        }

        @Override
        public Node next() {
            return consumeNextNode();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Removing nodes is not supported.");
        }

        private Node checkNextNode() {
            if (ivNextNode == null && ivNodeIterator != null) {
                ivNextNode = ivNodeIterator.nextNode();
                if (ivNextNode == null) {
                    ivNodeIterator.detach();
                    ivNodeIterator = null;
                }
            }
            return ivNextNode;
        }

        private Node consumeNextNode() {
            Node nextNode = checkNextNode();
            ivNextNode = null;
            return nextNode;
        }

        static NodeFilter getRootNodeFilter() {
            return pNode -> {
                /*if (pNode != null && pNode.getParentNode() instanceof Document) {
                    return NodeFilter.FILTER_SKIP;
                }*/
                return NodeFilter.FILTER_ACCEPT;
            };
        }
    }
}
