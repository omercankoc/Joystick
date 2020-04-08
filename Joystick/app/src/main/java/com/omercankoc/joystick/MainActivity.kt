package com.omercankoc.joystick

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    var relativeLayoutJoystick : RelativeLayout? = null
    var inner : ImageView? = null
    var outer : ImageView? = null
    var textViewXCoordinate : TextView? = null
    var textViewYCoordinate : TextView? = null
    var textViewAngle : TextView? = null
    var textViewDistance : TextView? = null
    var textViewDirection : TextView? = null

    var joystick : JoystickController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        relativeLayoutJoystick = findViewById<View>(R.id.relativeLayoutJoystick) as RelativeLayout

        textViewXCoordinate = findViewById<View>(R.id.textViewXCoordinate) as TextView
        textViewYCoordinate = findViewById<View>(R.id.textViewYCoordinate) as TextView
        textViewAngle = findViewById<View>(R.id.textViewAngle) as TextView
        textViewDistance = findViewById<View>(R.id.textViewDistance) as TextView
        textViewDirection = findViewById<View>(R.id.textViewDirection) as TextView

        joystick = JoystickController(applicationContext,relativeLayoutJoystick,R.drawable.inner)
        joystick!!.setStickSize(150,150)
        joystick!!.setLayoutSize(500,500)
        joystick!!.layoutAlpha = 150
        joystick!!.stickAlpha = 100
        joystick!!.offset = 90
        joystick!!.minimumDistance = 50

        relativeLayoutJoystick!!.setOnTouchListener { arg0, arg1 ->
            joystick!!.drawStick(arg1)
            if (arg1.action == MotionEvent.ACTION_DOWN
                || arg1.action == MotionEvent.ACTION_MOVE) {
                textViewXCoordinate!!.text = "X : " + joystick!!.x.toString()
                textViewYCoordinate!!.text = "Y : " + joystick!!.y.toString()
                textViewAngle!!.text = "Angle : " + joystick!!.angle.toString()
                textViewDistance!!.text = "Distance : " + joystick!!.distance.toString()
                val direction = joystick!!.get8Direction()
                if (direction == JoystickController.STICK_UP) {
                    textViewDirection!!.text = "Direction : Up"
                } else if (direction == JoystickController.STICK_UPRIGHT) {
                    textViewDirection!!.text = "Direction : Up Right"
                } else if (direction == JoystickController.STICK_RIGHT) {
                    textViewDirection!!.text = "Direction : Right"
                } else if (direction == JoystickController.STICK_DOWNRIGHT) {
                    textViewDirection!!.text = "Direction : Down Right"
                } else if (direction == JoystickController.STICK_DOWN) {
                    textViewDirection!!.text = "Direction : Down"
                } else if (direction == JoystickController.STICK_DOWNLEFT) {
                    textViewDirection!!.text = "Direction : Down Left"
                } else if (direction == JoystickController.STICK_LEFT) {
                    textViewDirection!!.text = "Direction : Left"
                } else if (direction == JoystickController.STICK_UPLEFT) {
                    textViewDirection!!.text = "Direction : Up Left"
                } else if (direction == JoystickController.STICK_NONE) {
                    textViewDirection!!.text = "Direction : Center"
                }
            } else if (arg1.action == MotionEvent.ACTION_UP) {
                textViewXCoordinate!!.text = "X :"
                textViewYCoordinate!!.text = "Y :"
                textViewAngle!!.text = "Angle :"
                textViewDistance!!.text = "Distance :"
                textViewDirection!!.text = "Direction :"
            }
            true
        }
    }
}
