package com.dataart.jbehave;

import net.serenitybdd.jbehave.SerenityStories;
import net.thucydides.core.ThucydidesSystemProperty;

public class JBehaveTestCase extends SerenityStories {

    public JBehaveTestCase() {
        System.setProperty(ThucydidesSystemProperty.PROPERTIES.getPropertyName(),"project.properties");
    }

}

