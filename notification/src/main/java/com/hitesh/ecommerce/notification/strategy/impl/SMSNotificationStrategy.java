@Slf4j
@Component("SMS")
public class SmsNotificationStrategy implements NotificationStrategy {
    @Override
    public void sendNotification(NotificationRequest request) {
        log.info("Sending SMS to {}: {}", request.getRecipient(), request.getMessage());
        // Add Twilio or SMS API
    }
}

