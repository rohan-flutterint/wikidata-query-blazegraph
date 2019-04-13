/**

Copyright (C) SYSTAP, LLC DBA Blazegraph 2013.  All rights reserved.

Contact:
     SYSTAP, LLC DBA Blazegraph
     2501 Calvert ST NW #106
     Washington, DC 20008
     licenses@blazegraph.com

This program is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; version 2 of the License.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package com.bigdata.rdf.sparql.ast.eval;

import java.util.Properties;

import com.bigdata.rdf.internal.NotMaterializedException;
import com.bigdata.rdf.store.AbstractTripleStore;


/**
 * Test case for https://jira.blazegraph.com/browse/BLZG-1591:
 * {@link NotMaterializedException} with ORDER BY clause (for InlineURIIvs).
 *
 * @author <a href="mailto:ms@metaphacts.com">Michael Schmidt</a>
 */
public class TestTicketT213375 extends AbstractDataDrivenSPARQLTestCase {

   public TestTicketT213375() {
   }

   public TestTicketT213375(String name) {
      super(name);
   }


  public void test_ticket_T213375a() throws Exception {
     new TestHelper("ticket-T213375a",// testURI,
           "ticket_T213375a.rq",// queryFileURL
           "ticket_T213375.nt",// dataFileURL
           "ticket_T213375.srx",// resultFileURL
           false /* checkOrder */
     ).runTest();
     System.out.println(this.store.dumpStore());
  }


//  public void test_ticket_1591b() throws Exception {
//      new TestHelper("ticket-1591b",// testURI,
//            "ticket_1591b.rq",// queryFileURL
//            "ticket_1591.nt",// dataFileURL
//            "ticket_1591.srx",// resultFileURL
//            true /* checkOrder */
//      ).runTest();
//   }

  @Override
  public Properties getProperties() {

      // Note: clone to avoid modifying!!!
      final Properties properties = (Properties) super.getProperties().clone();

      properties.setProperty(
          AbstractTripleStore.Options.VOCABULARY_CLASS,
          "com.bigdata.rdf.vocab.TestVocabulary_T213375");

      properties.setProperty(
          AbstractTripleStore.Options.INLINE_URI_FACTORY_CLASS,
          "com.bigdata.rdf.vocab.TestUriInlineFactory_T213375");

      return properties;

  }

}
