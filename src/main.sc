require: patterns.sc
   module =  sys.zb-common
theme: /

    state: NoMatch
        event!: noMatch
        a: Я не понял. Вы сказали: {{$request.query}}
        go!: /HowCanIHelp
            
    state: Start
        q!: $regex</start>
        a: Привет! Я бот-консультант кинотеатра "Художественный".
        go!: /HowCanIHelp

    state: HowCanIHelp
        a: Чем я могу помочь?
        buttons:
            "Расписание" -> /Timetable
            "Информация о фильмах" -> /MovieInformation
            "Адреса кинотеатров"  -> /Locations
            "Покупка билетов"  -> /BuyTicket
            "Возврат билетов"-> /ReturnInfo

    state: ReturnInfo
        a: Возврат билетов возможен не менее чем за 30 минут до начала сеанса. Если вы купили билет в нашем приложении, зайдите в раздел "Мои билеты" и выберите опцию "Отменить бронирование". Чтобы вернуть билет, купленный на сайте, пожалуйста позвоните по номеру +7 000 000 00 00, наша служба поддержки поможет вам осуществить возврат. Деньги вернутся на вашу карту в течение 5 дней. Если вы покупали билет в кассе кинотеатра, вам необходимо осуществить возврат через кассу. Если ваш сеанс начинается менее, чем через 30 минут, билет, к сожалению, вернуть не получится.
        a: Надеюсь, мой ответ был полезен :)
        buttons: 
            "Все супер!" -> /IssueSolved
            "У меня остались вопросы!" -> /AnythingElse?

    state: ReturnIssues
        q: * (не получается|не могу|не открыть|невозможно) * || fromState = "/ReturnInfo", onlyThisState = true
        q: * {(нет|не вижу|нету) [такую|такой|эту|этой] (кнопк*|опци*)} * || fromState = "/ReturnInfo", onlyThisState = true
        q: * {[приложение] (не работ*|вылет*|глюч*|глюк*|*лома*|проблем*|баг|багован*)} * || fromState = "/ReturnInfo", onlyThisState = true
        a: Правильно ли я понял, что у вас технические проблемы с возвратом?
        buttons:
            "Да" -> /IssueTrue
            "Нет" -> /HowCanIHelp

    state: ReturnTicket
        q: * {[как|хочу|хотел* бы|мож*|могу] [я|мы] [оформ*] (возвр*|верн*|сдать|сдавать) [билет]} *
        a: Правильно ли я понял, что хотите вернуть билет? 
        buttons:
            "Да" -> /ReturnInfo
            "Нет" -> /HowCanIHelp
        
    state: IssueTrue
        a: Спасибо! Пожалуйста, не выходите из чата. В ближайшее время вам ответит наша служба поддежрки.
        go!: /Operator
            
    state: AnythingElse?
        a: Могу ли я чем-то еще помочь?
        buttons:
            "Расписание" -> /Timetable
            "Информация о фильмах" -> /MovieInformation
            "Адреса кинотеатров"  -> /Locations
            "Покупка билетов"  -> /BuyTicket
            "Возврат билетов"-> /ReturnInfo
            "Оценить бота" -> /RateBot
        
    state: Timetable
        a: Вот расписание!
        go!: /AnythingElse?
    
    state: MovieInformation
        a: Вот информация! 
        go!: /AnythingElse?
        
    state: Locations
        a: Вот адреса!
        go!: /AnythingElse?
        
    state: BuyTicket
        a: Вот как купить билеты!
        go!: /AnythingElse?
        
    state: RateBot
        a: Вот как оценить бота!
        
    state: Operator
        a: Здравствуйте, я оператор!
        
    state: IssueSolved
        a: Ура!