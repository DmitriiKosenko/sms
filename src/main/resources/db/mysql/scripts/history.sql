CREATE TABLE sms_history (
ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
TELNUMBER INT NOT NULL,
DATESEND DATE NOT NULL,
STATUS TINYINT NOT NULL,
MESSAGE VARCHAR(1000)
);