CREATE TABLE sms.sms_history (
ID BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
TELNUMBER BIGINT NOT NULL,
DATESEND DATE NOT NULL,
STATUS TINYINT NOT NULL,
MESSAGE VARCHAR(1000)
);
