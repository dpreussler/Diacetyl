package de.jodamob.diacetyl;

import static de.jodamob.mockitoid.AndroidMocks.mockButton;
import static de.jodamob.mockitoid.AndroidMocks.mockEditField;
import static de.jodamob.mockitoid.AndroidMocks.mockImageButton;
import static de.jodamob.mockitoid.AndroidMocks.mockResources;
import static de.jodamob.mockitoid.AndroidMocks.mockTextView;
import static de.jodamob.mockitoid.AndroidMocks.mockView;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import android.content.res.Resources;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.reflect.Field;

import de.jodamob.reflect.SuperReflect;

/**
 * Diacetyl ... is added to some foods to impart its buttery flavor.
 * (source: wikipedia)
 */
public class Diacetyl {

    private final Resources resources = mockResources();

    public static void butterForTests(Object... instancesWithViews) {
        Diacetyl diacetyl = new Diacetyl();
        for (Object instanceWithViews : instancesWithViews) {
            diacetyl.bindForTests(instanceWithViews);
        }
    }

    public void bindForTests(Object instanceWithViews) {
        SuperReflect reflector = SuperReflect.on(instanceWithViews);
        Class<?> instanceWithViewsClass = instanceWithViews.getClass();
        do {
            bindAllViews(reflector, instanceWithViewsClass);
            instanceWithViewsClass = instanceWithViewsClass.getSuperclass();
        } while (!instanceWithViewsClass.isAssignableFrom(Object.class));
    }

    private void bindAllViews(SuperReflect reflector, Class<?> instanceWithViewsClass) {
        Field[] fields = instanceWithViewsClass.getDeclaredFields();
        for (Field field : fields) {
            if (isView(field)) {
                tryToMockView(reflector, field);
            }
        }
    }

    private boolean isView(Field field) {
        return View.class.isAssignableFrom(field.getType());
    }

    private void tryToMockView(SuperReflect reflector, Field field) {
        tryToSet(reflector, field, getMockFor(field.getType()));
    }

    private Object getMockFor(Class<?> fieldType) {
        if (fieldType.isAssignableFrom(TextView.class)) {
            return mockTextView(resources);
        } else if (fieldType.isAssignableFrom(EditText.class)) {
            return mockEditField("");
        } else if (fieldType.isAssignableFrom(Button.class)) {
            return mockButton();
        } else if (fieldType.isAssignableFrom(ImageButton.class)) {
            return mockImageButton();
        } else if (fieldType.isAssignableFrom(WebView.class)) {
            return mock(WebView.class);
        } else if (fieldType.isAssignableFrom(ProgressBar.class)) {
            return mock(ProgressBar.class);
        } else if (fieldType.isAssignableFrom(View.class)) {
            View view = mockView();
            when(view.getResources()).thenReturn(resources);
            return view;
        }
        return null;
    }

    private void tryToSet(SuperReflect reflector, Field field, Object mock) {
        try {
            reflector.setIfNull(field.getName(), mock);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
