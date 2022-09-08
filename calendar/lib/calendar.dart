import 'package:flutter/material.dart';
import 'package:table_calendar/table_calendar.dart';

class Calendar extends StatefulWidget {
  @override
  _CalendarState createState() => _CalendarState();
}
class _CalendarState extends State<Calendar> {
  CalendarFormat format = CalendarFormat.month;
  DateTime selectedDay = DateTime.now();
  DateTime focusedDay = DateTime.now();

  @override
  Widget build(BuildContext context){
    return Scaffold(
      appBar: AppBar(
        title: Text("Calendar"),
        centerTitle:true,
      ),
      body: TableCalendar(
      focusedDay: selectedDay,
      firstDay: DateTime(1990),
      lastDay:DateTime(2050),
      calendarFormat: format,
      onFormatChanged: (CalendarFormat _format){
        setState((){
          format = _format;
        });
      },
      startingDayOfWeek: StartingDayOfWeek.sunday,
      daysOfWeekVisible: true,
      onDaySelected: (DateTime selectDay, DateTime focusDay){
        setState((){
          selectedDay = selectDay;
          focusedDay = focusDay;
        });
        print(focusedDay);
      },
      calendarStyle: CalendarStyle(
        isTodayHighlighted: true,
        selectedDecoration: BoxDecoration(
          color: Colors.blue,
          shape: BoxShape.rectangle,
          borderRadius: BorderRadius.circular(5.0)
      ),
      selectedTextStyle: TextStyle(color: Colors.white),
      todayDecoration: BoxDecoration(
        color: Colors.purpleAccent,
        shape: BoxShape.rectangle,
        borderRadius: BorderRadius.circular(5.0),

      )
      ),
      headerStyle: HeaderStyle(
        formatButtonVisible: true,
        titleCentered: true,
        formatButtonShowsNext:false,
        formatButtonDecoration: BoxDecoration(
          color: Colors.blue,
          borderRadius: BorderRadius.circular(5.0)
        ),
        formatButtonTextStyle: TextStyle(
          color: Colors.white,
        ),
        leftChevronVisible: false,
        rightChevronVisible: false, headerPadding: EdgeInsets.symmetric(horizontal: 5.0,vertical: 10.0),
      ),
      ),
    );

  }
}