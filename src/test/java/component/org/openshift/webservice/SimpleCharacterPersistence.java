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

package component.org.openshift.webservice;

import java.util.UUID;

import javax.enterprise.context.RequestScoped;

import org.openshift.model.Character;
import org.openshift.webservice.PersistCharacter;

@RequestScoped
public class SimpleCharacterPersistence implements PersistCharacter {
    @Override
    public String saveCharacter(Character savableCharacter) {
        return UUID.randomUUID().toString();
    }
}
