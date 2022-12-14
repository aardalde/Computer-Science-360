# Aaron Alden
# CSCI 360
# Dr. Ericson
# November 15, 2022

import maskpass
import smtplib
import sys
from email.mime.text import MIMEText

print("")
useremail = input("Enter your email address: ")
userpassword = maskpass.askpass(mask="")
recipientemail = input("Enter the person's email you wish to send to: ")
emailsubject = input("Enter the email subject: ")
emailcontent = input("Enter the body of the email: ")

try:
    msg = MIMEText(emailcontent, 'plain')
    msg['Subject'] = emailsubject
    msg['From'] = useremail
    msg['To'] = recipientemail

    server = smtplib.SMTP('smtp.gmail.com:587')
    server.ehlo()
    server.starttls()
    server.login(useremail, userpassword)
    try:
        server.send_message(msg)
    finally:
        server.quit()
except:
    sys.exit("EMAIL FAILED TO SEND...TRY ENTERING CREDENTIALS AGAIN")