/*
 * AcceleroJoystick
 *
 *  Created on: May 26, 2011
 *      Author: Dmytro Baryskyy
 */

package com.microsoft.cfd.limelight.DroneInterface.flight.ui.hud;

import android.content.Context;
import com.microsoft.cfd.limelight.R;



public class AcceleroJoystick 
	extends JoystickBase
{
	public AcceleroJoystick(Context context, Align align, boolean absolute) 
	{
		super(context, align, absolute);
	}
	
	
	@Override
	protected int getBackgroundDrawableId() 
	{
		// Transparent background
		return R.drawable.accelero_background;
	}


	@Override
	protected int getTumbDrawableId() 
	{
		return R.drawable.joystick_gyro;
	}

	
	@Override
	protected void onActionMove(float x, float y) 
	{
		// Ignore action move.
	}
}
