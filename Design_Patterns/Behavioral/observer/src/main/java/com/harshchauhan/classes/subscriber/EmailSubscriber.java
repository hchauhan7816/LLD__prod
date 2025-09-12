package com.harshchauhan.classes.subscriber;

public class EmailSubscriber implements Subscriber {

    private final String email;

    public EmailSubscriber(String email) {
        this.email = email;
    }

    @Override
    public void update(String video) {
        System.out.println("Email send to :: "  + email + " :: for video :: " + video);
    }

}
