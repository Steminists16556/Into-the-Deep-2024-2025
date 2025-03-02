package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
//import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class driveChassis extends OpMode {

    //Hardware declarations for chassis motors
    DcMotor backLeft;
    DcMotor frontLeft;
    DcMotor backRight;
    DcMotor frontRight;


    //Hardware declarations for the arm motors
    DcMotor upSlider;
    DcMotor extendSlider;

    //Hardware declarations for servos
    Servo rightServoDomain;
    Servo leftServoDomain;
    Servo rightServoRange;
    Servo leftServoRange;

    //Hardware declarations for hanging motors
    DcMotor upLeft;
    DcMotor upRight;

    double hangTicks = 5281.1;
    double hangTarget;


    public void init() {

        //Hardware declarations for servos
        backLeft = hardwareMap.dcMotor.get("backLeft");
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        frontRight = hardwareMap.dcMotor.get("frontRight");

        //Hardware mapping for arm motors
        extendSlider = hardwareMap.dcMotor.get("extendSlider");
        //extendSlider.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        upSlider = hardwareMap.dcMotor.get("upSlider");
        //upSlider.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //upSlider.setMode(DcMotor.RunMode.RESET_ENCODERS);
        upSlider.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Hardware mapping for servo motors
        rightServoDomain = hardwareMap.servo.get("rightServoDomain");
        leftServoDomain = hardwareMap.servo.get("leftServoDomain");
        rightServoRange = hardwareMap.servo.get("rightServoRange");
        leftServoRange = hardwareMap.servo.get("leftServoRange");

        //Hardware mapping for hanging
        upRight = hardwareMap.dcMotor.get("upRight");
        upLeft = hardwareMap.dcMotor.get("upLeft");

    }

    //Start of loop
    public void loop() {

        //Code for chassis
        double forward = gamepad1.left_stick_y;
        double strafe = gamepad1.right_stick_x;
        double turn = 0.7 * gamepad1.left_stick_x;

        //Code for slider to extend
        double up = -0.5 * gamepad2.left_stick_y;
        double out = 0.8 * gamepad2.right_stick_y;

        double up2 = 0.75 * gamepad1.right_stick_y;


        //Power for chassis
        backLeft.setPower(forward + strafe - turn);
        frontLeft.setPower(forward - strafe - turn);
        backRight.setPower(-forward + strafe - turn);
        frontRight.setPower(-forward - strafe - turn);

        //Power for horizontalSlider
        //horizontalSlider.setPower(out);

        //Power for extendSlider
        extendSlider.setPower(out);
        upSlider.setPower(up);

        upLeft.setPower(up2);
        upRight.setPower(-up2);

        /*
        //Encoder defining for extendSlider
        double sliderTicks = 860.32;
        double sliderTarget;


        //Encoder for extendSlider
        extendSlider.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        extendSlider.setDirection(DcMotorSimple.Direction.REVERSE);
        extendSlider.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        extendSlider.setMode(DcMotor.RunMode.RESET_ENCODERS);
*/
        //Encoder mapping for up motor
        //origin
       /* if(gamepad2.dpad_down) {
            full(0.2);
        }

        //go into the submersable
        if(gamepad2.dpad_up) {
            full(0.5);
        }

        //reach high chamber and basket
        if(gamepad2.dpad_right) {
            full(0.8);
        }

        telemetry.addData("Motor arm ticks: ", upSlider.getCurrentPosition());

*/

        //Servos for opening and closing the claw
        //Left Bumper for opening claw
        if (gamepad2.left_bumper) {
            rightServoDomain.setPosition(0.1);
            leftServoDomain.setPosition(0.9);
        }

        //Right Bumper for closing claw
        if (gamepad2.right_bumper) {
            rightServoDomain.setPosition(0.5);
            leftServoDomain.setPosition(0.5);
        }

        //Servos for moving the claw up and down
        //Servos for setting claw
        if (gamepad2.b) {
            rightServoRange.setPosition(0.5);
            //rightServoRange.setDirection(Servo.Direction.REVERSE);
            leftServoRange.setPosition(0.5);
            //rightServoRange.setDirection(Servo.Direction.REVERSE);
        }

        //Servos for raising claw
        if (gamepad2.y) {
            rightServoRange.setPosition(0.7);
            leftServoRange.setPosition(0.3);
        }

        //Servos for lowering claw
        if (gamepad2.a) {
            rightServoRange.setPosition(0.3);
            leftServoRange.setPosition(0.7);
        }

        //Starting position
        if (gamepad1.a) {
            full(-.4);
            full2(.4);


















        }

        //Reaching chamber
        if (gamepad1.x) {
            full(-1.70);
            full2(1.70);
        }

        //hang
        if (gamepad1.b) {
            full(-.3);
            full2(.3);
        }

    }

    //End of loop

    public void full(double turnage) {
        hangTarget = hangTicks * turnage;
        upLeft.setTargetPosition((int) hangTarget);
        upLeft.setPower(.75);
        upLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);


    }

    public void full2(double turnage) {
        hangTarget = hangTicks * turnage;
        upRight.setTargetPosition((int) hangTarget);
        upRight.setPower(.75);
        upRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

}

