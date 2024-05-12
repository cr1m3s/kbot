# kbot -- telegram bot project


Розробка функціонального Telegram-бота з кореневою командою та налаштуваннями. Він матиме можливість обробляти повідомлення від користувачів та відповідати на них:

    Мова Golang
    Фреймворки github.com/spf13/cobra та gopkg.in/telebot.v3
    Реалізувати обробники повідомлень для бота, які будуть відповідати на повідомлення в Telegram.
    Створити функції-обробники повідомлень бота.
    Додати ці функції до методів об'єкта telebot.Bot.
    Обробляти повідомлення відповідно до їх типу та вмісту.



## Залежності: 
* "github.com/spf13/cobra"
* "gopkg.in/telebot.v3"

* Для роботи боту потрібно створити змінну середовища "TELE_TOKEN".


--- 

* Білд: 
`go build -ldflags "-X="github.com/cr1m3s/kbot/cmd.appVersion={bot_version}`

* Посилання:  `t.me/cr1m3s_k_bot`

v1.0.2

```
/start hello --> Hello, I'm kbot v1.0.2!
```

## Monitoring

1. Copy repository:
`git clone https://github.com/cr1m3s/kbot`

2. Setup TELE_TOKEN and METRICS_HOST env variables.

3. Compose monitor:
`docker-compose -f otel/docker-compose.yaml up`

4. If everything correct:
[compose](./pic/monitor.png)
[fluentbit](./pic/fluentbit.png)

5. Check monitor at METRIC_HOST address:
[mectrics](./pic/grafana.png)
