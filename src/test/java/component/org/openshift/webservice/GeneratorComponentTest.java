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

import java.util.HashMap;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openshift.model.Character;
import org.openshift.webservice.Generator;
import org.openshift.webservice.PersistCharacter;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Arquillian.class)
public class GeneratorComponentTest {
    @Deployment
    public static Archive<?> createTestArchive() {
        final WebArchive webArchive = ShrinkWrap.create(WebArchive.class);
        webArchive.addClasses(Generator.class, Character.class, PersistCharacter.class, SimpleCharacterPersistence.class);
        webArchive.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

        // Adding deps
        webArchive.addAsLibraries(Maven.resolver().resolve("com.google.code.gson:gson:2.8.2").withTransitivity().asFile());
        webArchive.addAsLibraries(Maven.resolver().resolve("org.assertj:assertj-core:3.5.2").withTransitivity().asFile());

        return webArchive;
    }

    @Test
    public void persistCharTest(Generator charGenerator) throws Exception {
        final HashMap characterReturn = charGenerator.MakeACharacterForMongo(null);

        assertThat(characterReturn).containsOnlyKeys("strength", "intelligence", "charisma", "wisdom", "dexterity",
                "constitution", "loc", "race", "hitpoints", "name", "goldpieces", "playerclass", "id");
    }
}
