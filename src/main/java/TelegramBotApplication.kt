import org.telegram.telegrambots.ApiContextInitializer
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow

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
        if(update.hasMessage()) {
            if (update.message.text == "/start") {
                val keyboard = ReplyKeyboardMarkup()
                keyboard.keyboard = listOf(
                        KeyboardRow().apply {
                            add(KeyboardButton("Філіали"))
                        }, KeyboardRow().apply {
                    add(KeyboardButton("Замовлення"))

                }
                )
                execute(SendMessage()
                        .setReplyMarkup(keyboard)
                        .setChatId(update.message.chatId)
                        .setText("Hi!"))
            }
            if (update.message.text == "Філіали") {
                execute(SendMessage()
                        .setText("Виберіть місто:")
                        .setChatId(update.message.chatId)
                        .setReplyMarkup(InlineKeyboardMarkup().apply {
                            keyboard = listOf(
                                    listOf(
                                            InlineKeyboardButton("Івано-Франківськ").apply { callbackData = "Ivano-Frankivsk" },
                                            InlineKeyboardButton("Київ").apply { callbackData = "Kiev" })
                            )
                        }))
            }
        }
        if (update.hasCallbackQuery()){
            if(update.callbackQuery.data == "Ivano-Frankivsk"){
                val chatId = update.callbackQuery.message.chatId
                execute(SendMessage()
                    .setChatId(chatId)
                    .setText("Адреса в Івано-Франківську")
                    .setReplyMarkup(InlineKeyboardMarkup().apply {
                        keyboard = listOf(listOf(InlineKeyboardButton("Сторінка філіалу").apply {
                            url = "https://monobanking.com.ua/map/yvano-frankovsk/"
                        }))
                    })
            )
                execute(SendPhoto()
                        .setCaption("IF City")
                        .setChatId(chatId)
                        .setPhoto("https://www.google.com/maps/place/%D0%98%D0%B2%D0%B0%D0%BD%D0%BE-%D0%A4%D1%80%D0%B0%D0%BD%D0%BA%D0%BE%D0%B2%D1%81%D0%BA,+%D0%98%D0%B2%D0%B0%D0%BD%D0%BE-%D0%A4%D1%80%D0%B0%D0%BD%D0%BA%D0%BE%D0%B2%D1%81%D0%BA%D0%B0%D1%8F+%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%82%D1%8C,+76000/@48.9117518,24.6470894,12z/data=!3m1!4b1!4m5!3m4!1s0x4730c16c34b0381d:0xd6d32394e59e41c2!8m2!3d48.922633!4d24.711117"))
            }
        }
    }
}

