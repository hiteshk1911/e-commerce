@Slf4j
@Component("PUSH")
public class PushNotificationStrategy implements NotificationStrategy {
    @Override
    public void sendNotification(NotificationRequest request) {
        log.info("Sending PUSH to {}: {}", request.getRecipient(), request.getMessage());
        // Add FCM or custom logic
    }
}

