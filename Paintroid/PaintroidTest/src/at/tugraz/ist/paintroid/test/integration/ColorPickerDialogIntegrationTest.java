package at.tugraz.ist.paintroid.test.integration;

import android.widget.LinearLayout;
import at.tugraz.ist.paintroid.MainActivity;
import at.tugraz.ist.paintroid.test.utils.PrivateAccess;
import at.tugraz.ist.paintroid.ui.Toolbar;
import at.tugraz.ist.paintroid.ui.implementation.DrawingSurfaceImplementation;

public class ColorPickerDialogIntegrationTest extends BaseIntegrationTestClass {

	protected Toolbar toolbar;

	public ColorPickerDialogIntegrationTest() throws Exception {
		super();
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		toolbar = (Toolbar) PrivateAccess.getMemberValue(MainActivity.class, mMainActivity, "mToolbar");
	}

	public void testColorPickerDialogOnBackPressed() {
		assertTrue("Waiting for DrawingSurface", mSolo.waitForView(DrawingSurfaceImplementation.class, 1, TIMEOUT));
		mSolo.clickOnView(mToolBarButtonOne);
		assertTrue("Waiting for ColorPicerDialog", mSolo.waitForView(LinearLayout.class, 1, TIMEOUT));
		mSolo.goBack();
		assertTrue("Waiting for DrawingSurface", mSolo.waitForView(DrawingSurfaceImplementation.class, 1, TIMEOUT));

		int oldColor = toolbar.getCurrentTool().getDrawPaint().getColor();
		mSolo.clickOnView(mToolBarButtonOne);
		assertTrue("Waiting for ColorPicerDialog", mSolo.waitForView(LinearLayout.class, 1, TIMEOUT));
		mSolo.clickOnButton(5);
		mSolo.goBack();
		assertTrue("Waiting for DrawingSurface", mSolo.waitForView(DrawingSurfaceImplementation.class, 1, TIMEOUT));
		int newColor = toolbar.getCurrentTool().getDrawPaint().getColor();
		assertTrue("After pressing back, color should be the same", oldColor == newColor);
	}
}