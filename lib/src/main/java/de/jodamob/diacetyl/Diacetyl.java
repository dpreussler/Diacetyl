package de.jodamob.diacetyl;

import static de.jodamob.mockitoid.AndroidMocks.mockAutoCompleteEditField;
import static de.jodamob.mockitoid.AndroidMocks.mockButton;
import static de.jodamob.mockitoid.AndroidMocks.mockCardView;
import static de.jodamob.mockitoid.AndroidMocks.mockCheckBox;
import static de.jodamob.mockitoid.AndroidMocks.mockEditText;
import static de.jodamob.mockitoid.AndroidMocks.mockFrameLayout;
import static de.jodamob.mockitoid.AndroidMocks.mockImageButton;
import static de.jodamob.mockitoid.AndroidMocks.mockLinearLayout;
import static de.jodamob.mockitoid.AndroidMocks.mockProgressBar;
import static de.jodamob.mockitoid.AndroidMocks.mockRecyclerView;
import static de.jodamob.mockitoid.AndroidMocks.mockRelativeLayout;
import static de.jodamob.mockitoid.AndroidMocks.mockResources;
import static de.jodamob.mockitoid.AndroidMocks.mockTextView;
import static de.jodamob.mockitoid.AndroidMocks.mockView;
import static de.jodamob.mockitoid.AndroidMocks.mockWebView;
import static org.mockito.Mockito.mock;

import android.content.res.Resources;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
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

    private void bindForTests(Object instanceWithViews) {
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
            return mockEditText("");
        } else if (fieldType.isAssignableFrom(AutoCompleteTextView.class)) {
            return mockAutoCompleteEditField("");
        } else if (fieldType.isAssignableFrom(Button.class)) {
            return mockButton();
        } else if (fieldType.isAssignableFrom(ImageButton.class)) {
            return mockImageButton();
        } else if (fieldType.isAssignableFrom(CheckBox.class)) {
            return mockCheckBox();
        } else if (fieldType.isAssignableFrom(WebView.class)) {
            return mockWebView();
        } else if (fieldType.isAssignableFrom(ProgressBar.class)) {
            return mockProgressBar();
        } else if (fieldType.isAssignableFrom(RecyclerView.class)) {
            return mockRecyclerView();
        } else if (fieldType.isAssignableFrom(CardView.class)) {
            return mockCardView();
        } else if (fieldType.isAssignableFrom(LinearLayout.class)) {
            return mockLinearLayout();
        } else if (fieldType.isAssignableFrom(RelativeLayout.class)) {
            return mockRelativeLayout();
        } else if (fieldType.isAssignableFrom(FrameLayout.class)) {
            return mockFrameLayout();
        } else if (fieldType.isAssignableFrom(View.class)) {
            View view = mockView(resources);
            return view;
        } else {
            // unknown or custom views
            return mock(fieldType);
        }
    }

    private void tryToSet(SuperReflect reflector, Field field, Object mock) {
        try {
            reflector.setIfNull(field.getName(), mock);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
