package com.jmms.application;

import javafx.embed.swing.JFXPanel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class MembersWindowTest {

    @Before
    public void setUp() throws Exception {
        new JFXPanel();
    }

    @Test
    public void window_creation() throws Exception {
        MembersWindow window = new MembersWindow();
        assertNotNull(window);
    }
}