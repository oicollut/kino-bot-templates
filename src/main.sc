require: patterns.sc
   module =  sys.zb-common
theme: /

    state: NoMatch
        event!: noMatch
        if: $session.nomatch < 3
            a: Я не понял. Вы сказали: "{{$request.query}}".
        script: $session.nomatch += 1
        if: $session.nomatch > 3
            a: Что-то я вас опять не понимаю... 
            script: $session.nomatch = 0
            go!: /HowCanIHelp
            
    state: Start
        q!: $regex</start>
        go!: /HowCanIHelp

    state: HowCanIHelp
            a: Привет!
            buttons:
                "Расписание" -> /Timetable
                "Информация о фильмах" -> /MovieInformation
                "Адреса кинотеатров"  -> /Locations
                "Покупка билетов"  -> /BuyTicket
                "Возврат билетов"-> /ReturnInfo

    state: ReturnTicket
        q!: {(*возвр*|*верн*) [билет] *}
        a: Правильно ли я понял, что хотите вернуть билет? 
        buttons:
            "Да" -> /ReturnInfo
            "Нет" -> /HowCanIHelp
    
    state: ReturnInfo
        a: Вот правила возврата билетов. Если ваш сеанс начинается менее, через 30 минут, билет, к сожалению, вернуть не получится.
        go!: /AnythingElse?
        
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
    
    state: MovieInformation
        a: Вот информация! 
        
    state: Locations
        a: Вот адреса!
        
    state: BuyTicket
        a: Вот как купить билеты!
        
    state: RateBot
        a: Вот как оценить бота!
    