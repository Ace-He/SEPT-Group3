import 'package:flutter/material.dart';
import 'package:table_calendar/table_calendar.dart';

class Calendar extends StatefulWidget {
    @override
    _CalendarState createState() => _CalendarState();
}

class Calendar extends State<Calendar> {
    CalendarFormat format = CalendarFormat.month;
    DateTime selectedDay = DateTime.now();
    DateTime focusedDay = DateTime.now();

    @override
    Widget build(BuildContext context) {
        return Scaffold(
            appBar: AppBar(
                title: Text("Calendar"),
                centerTitle:true,
            ), // AppBar
            body:TableCalendar(
                focusedDay:DateTime.now(),
                firstDay:DateTime(1990),
                lastDay:DateTime(2050)
                calendarformat: format,
                onFormatChanged: ((CalendarFormat _format){
                    setState(() {
                        format = _format;
                    });
                },
                startingDayofWeek: StartingDayOfWeek.sunday,
                daysOfWeekVisible: true,
                onDaySelected: (DateTime selctDay, DateTime focusDay) {
                    
                }
            ), // TableCalendar
            );
              
    }
}