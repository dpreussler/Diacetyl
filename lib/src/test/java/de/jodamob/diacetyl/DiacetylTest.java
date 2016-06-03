package de.jodamob.diacetyl;

import static de.jodamob.mockitoid.AndroidMocks.mockTextView;
import static org.fest.assertions.api.Assertions.assertThat;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.junit.Test;

public class DiacetylTest {

    Diacetyl tested = new Diacetyl();
    TestClass exampleClass = new TestClass();

    @Test
    public void should_fill_views() {

        tested.bindForTests(exampleClass);
        assertThat(exampleClass.normalView).isNotNull();
        assertThat(exampleClass.supportView).isNotNull();
    }

    @Test
    public void should_fill_custom_views() {
        tested.bindForTests(exampleClass);
        assertThat(exampleClass.customView).isNotNull();
    }

    @Test
    public void should_not_remove_pre_filled_views() {
        tested.bindForTests(exampleClass);
        assertThat(exampleClass.nonNulView.getText()).isEqualTo("preset");
    }

    static class TestClass {
        View normalView;
        RecyclerView supportView;
        MyCustomView customView;
        TextView nonNulView = mockTextView("preset");
    }

    static class MyCustomView extends View {
        public MyCustomView(Context context) {
            super(context);
        }
    }
}