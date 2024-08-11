package com.einvoice.sign;

/* Copyright 2024 VLCHEONG
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

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

interface XML {

    String XSLT = """
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

    static String minify(String xml) throws TransformerException {
        Source xsltSource = new StreamSource(new StringReader(XSLT));
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(xsltSource);
        Source source = new StreamSource(new StringReader(xml));
        StringWriter writer = new StringWriter();
        Result result = new StreamResult(writer);
        transformer.transform(source, result);
        return writer.toString();
    }

    static String canonicalize(String xml, String algorithm) throws
        ParserConfigurationException, IOException, SAXException,
        InvalidAlgorithmParameterException, NoSuchAlgorithmException,
        TransformException {
        Document doc = toDocument(xml);
        Data data = new NodeSetDataImpl(doc, node -> NodeFilter.FILTER_ACCEPT);
        XMLSignatureFactory signatureFactory = XMLSignatureFactory.getInstance("DOM");
        CanonicalizationMethod canonicalizationMethod = signatureFactory.newCanonicalizationMethod(algorithm, (C14NMethodParameterSpec) null);
        OctetStreamData streamData = (OctetStreamData) canonicalizationMethod.transform(data, null);
        return new String(streamData.getOctetStream().readAllBytes(), StandardCharsets.UTF_8);
    }

    static Document toDocument(String xml) throws ParserConfigurationException, IOException, SAXException {
        byte[] bytes = xml.getBytes(StandardCharsets.UTF_8);
        try (ByteArrayInputStream input = new ByteArrayInputStream(bytes)) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder docBuilder = factory.newDocumentBuilder();
            return docBuilder.parse(input);
        }
    }

    final class NodeSetDataImpl implements NodeSetData<Node>, Iterator<Node> {

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
            return nextNode();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove node is unsupported.");
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

        private Node nextNode() {
            Node nextNode = checkNextNode();
            ivNextNode = null;
            return nextNode;
        }
    }
}
