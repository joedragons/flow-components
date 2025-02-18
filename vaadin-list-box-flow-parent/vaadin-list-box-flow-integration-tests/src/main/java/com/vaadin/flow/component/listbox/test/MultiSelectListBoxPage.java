/*
 * Copyright 2000-2023 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.vaadin.flow.component.listbox.test;

import java.util.HashSet;
import java.util.Set;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.router.Route;

@Route("vaadin-list-box/multi-select")
public class MultiSelectListBoxPage extends Div {

    public MultiSelectListBoxPage() {
        MultiSelectListBox<String> listbox = new MultiSelectListBox<>();
        listbox.setItems("foo", "bar", "baz", "qux");

        Span fromClientSpan = new Span();
        fromClientSpan.setId("fromClient");

        Div valueChanges = new Div();
        valueChanges.add(new Text("value:"));
        valueChanges.setId("valueChanges");

        listbox.addValueChangeListener(e -> {
            valueChanges.add(new Paragraph(formatValue(e.getValue())));
            fromClientSpan.setText(e.isFromClient() + "");
        });

        Set<String> valueToSet = new HashSet<>();
        valueToSet.add("bar");
        valueToSet.add("qux");
        NativeButton setValueButton = new NativeButton("set value bar qux",
                e -> listbox.setValue(valueToSet));
        setValueButton.setId("setValue");

        add(listbox, setValueButton,
                new Div(new Span("fromClient: "), fromClientSpan),
                valueChanges);
    }

    private String formatValue(Set<String> value) {
        return String.join(", ", value);
    }

}
