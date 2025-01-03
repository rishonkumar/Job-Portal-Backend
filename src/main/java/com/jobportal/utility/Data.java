package com.jobportal.utility;

public class Data {

    public static String getMessageOtp(String otp,String name) {
        String emailTemplate = """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>OTP Email</title>
                    <style>
                        body {
                            font-family: Arial, sans-serif;
                            background-color: #f4f4f4;
                            margin: 0;
                            padding: 0;
                        }
                        .email-container {
                            max-width: 600px;
                            margin: 20px auto;
                            background-color: #ffffff;
                            border-radius: 8px;
                            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
                            overflow: hidden;
                        }
                        .email-header {
                            background-color: #4CAF50;
                            color: white;
                            text-align: center;
                            padding: 20px;
                        }
                        .email-body {
                            padding: 20px;
                            color: #333333;
                            line-height: 1.6;
                        }
                        .otp-box {
                            display: inline-block;
                            font-size: 1.5em;
                            color: #4CAF50;
                            font-weight: bold;
                            background-color: #f9f9f9;
                            border: 1px solid #dddddd;
                            border-radius: 4px;
                            padding: 10px 20px;
                            margin: 20px 0;
                        }
                        .email-footer {
                            text-align: center;
                            font-size: 0.9em;
                            color: #777777;
                            padding: 20px;
                            border-top: 1px solid #eeeeee;
                        }
                    </style>
                </head>
                <body>
                    <div class="email-container">
                        <div class="email-header">
                            <h1>Your OTP Code</h1>
                        </div>
                        <div class="email-body">
                            <p>Dear <strong>name</strong>,</p>
                            <p>We received a request to verify your email address. Use the OTP below to complete the process:</p>
                            <div class="otp-box">%s</div>
                            <p>If you didn’t request this, please ignore this email or contact support if you have any concerns.</p>
                            <p>Thank you,<br>Name %s</p>
                        </div>
                        <div class="email-footer">
                            &copy; %s %s. All rights reserved.
                        </div>
                    </div>
                </body>
                </html>
                """;

        return String.format(otp,java.time.Year.now());

    }
}
