/*
 * JBoss, Home of Professional Open Source
 * Copyright 2018, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the &quot;License&quot;);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an &quot;AS IS&quot; BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package unit.org.openshift.model;

import org.junit.Test;
import org.openshift.model.Character;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class CharacterTest {

    @Test
    public void testNoArgConstructor() {
        final Character someChar = new Character();

        assertThat(someChar, is(notNullValue(Character.class)));
    }

    @Test
    public void getAllAttributes() {
        final Character someChar = new Character();
        assertThat(someChar.getAllAttributes(), is(notNullValue()));
    }
}
