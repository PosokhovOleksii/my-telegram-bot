import org.telegram.telegrambots.ApiContextInitializer
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

class TelegramBotApplication

fun main(){
    ApiContextInitializer.init();
    val bot = Bot()
    TelegramBotsApi().registerBot(Bot());

}

class Bot: TelegramLongPollingBot() {
    override fun getBotUsername() = "MediaAdviserbot"

    override fun getBotToken() = "1410032442:AAEb4DpLUjRyoMKbbee8VNNlbB-Aq-YABHM"

    override fun onUpdateReceived(update: Update) {
       if (update.message.text == "/start"){
           execute(SendMessage().setChatId(update.message.chatId).setText("Hi!"))
       }
    }
}

