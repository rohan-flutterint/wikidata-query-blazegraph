package com.bigdata.rdf.vocab;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

import org.openrdf.model.URI;
import org.openrdf.model.impl.URIImpl;

public class TestVocabularyDecl_T213375 implements VocabularyDecl {

    static private final URI[] uris = new URI[]{
        new URIImpl("http://test.com/")
    };

    public TestVocabularyDecl_T213375() {
    }

    public Iterator<URI> values() {

        return Collections.unmodifiableList(Arrays.asList(uris)).iterator();

    }

}
