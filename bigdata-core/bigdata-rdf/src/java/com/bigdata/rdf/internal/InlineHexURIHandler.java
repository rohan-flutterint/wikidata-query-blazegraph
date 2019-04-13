/**

Copyright (C) SYSTAP, LLC DBA Blazegraph 2006-2016.  All rights reserved.

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
package com.bigdata.rdf.internal;

import java.math.BigInteger;
import java.util.Random;

import com.bigdata.rdf.internal.impl.literal.AbstractLiteralIV;
import com.bigdata.rdf.internal.impl.literal.XSDIntegerIV;
import com.bigdata.rdf.model.BigdataLiteral;

/**
 *
 * Inline URI Handler to handle URI's in the form of a Hex UUID such as:
 *
 *  <pre>
 *   http://blazegraph.com/element/d95dde070543a0e0115c8d5061fce6754bb82280
 *  </pre>
 *
 *  {@link https://phabricator.wikimedia.org/T213375}
 *
 * @author igorkim78@gmail.com
 *
 */
public class InlineHexURIHandler extends InlineURIHandler {

    /**
     * Default URI namespace for inline UUIDs.
     */
    public static final String NAMESPACE = "urn:hex:big:";

    public InlineHexURIHandler(final String namespace) {
        super(namespace);
    }

    @SuppressWarnings("rawtypes")
    protected AbstractLiteralIV createInlineIV(final String localName) {

        if (localName == null) {
            return null;
        }

        try {
		Random rnd = new Random();
            BigInteger value = new BigInteger(localName,16);
//            for (int i = 8*20; i<8*250; i++) {
//            	if ((rnd.nextInt()&1) == 0)
//            	value = value.setBit(i);
//            }
			return new XSDIntegerIV(value);
        } catch (IllegalArgumentException ex) {
            /*
             * Could not parse localName into a BigInteger.  Fall through to TermIV.
             */
            return null;
        }

    }

    @SuppressWarnings("rawtypes")
	@Override
	public String getLocalNameFromDelegate(
			AbstractLiteralIV<BigdataLiteral, ?> delegate) {

	final BigInteger bigint = ((XSDIntegerIV) delegate).getInlineValue();

		final String localName = bigint.toString(16);

		return localName;
	}
}
