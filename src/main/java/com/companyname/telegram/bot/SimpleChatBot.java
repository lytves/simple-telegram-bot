package com.companyname.telegram.bot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class SimpleChatBot extends TelegramLongPollingBot{
    public static void main(String[] args) {

        //initialize API Context
        ApiContextInitializer.init();

        //Instantiate Telegram Bots API
        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            //register our bot
            botsApi.registerBot(new SimpleChatBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    @Override
    public String getBotUsername() {
        // your bot's name
        return "your bot's name";
    }

    @Override
    public String getBotToken() {
        // // your bot's token
        return "your bot's token";
    }

    @Override
    public void onUpdateReceived(Update update) {

        /*
            Our bot resend a user's message to himself
         */

        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
                    .setChatId(update.getMessage().getChatId())
                    .setText(update.getMessage().getText());
            try {
                sendMessage(message); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

}
