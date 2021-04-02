# University Enlistment With Unit Testing

## Description

A requirement for a course in CIIT College of Arts and Technology called Software Engeneering 1.

### Machine Problem Requirements

- Requirements done by the instructor:

  - A student is identified by his/her student number, which is non-negative integer.
  - Student enlists in one or more sections. The studentmay have already previously enlisted in other sections.
  - A section is identified by its section ID, which is alphanumeric.
  - A student cannot enlist in the same section more thanonce.
  - The system makes sure that the student cannot enlistin any section that has a conflict with previously enlisted sections.
  - A student may not enlist in a section if its schedule overlaps with the schedule of any of its currently enlisted sections.
  - Days: Mon/Thu, Tue/Fri, Wed/Sat

- Periods may be of any duration of 30-min increments, w/in the hours of 8:30am -5:30pm.
- Periods may begin and may end at the top of each hour(9:00, 10:00, 11:00...) or at the bottom of each hour (9:30, 10:30, 11:30...).
- End of a period may not be on or before the start of the period.
  - Examples
  - Valid Periods: 8:30am - 10:00am, 9:00am - 12:00nn,2:30pm - 4:30pm, 9:00am -10:30am...
- Invalid Periods: 8:45am - 10:15am, 12:01pm - 12:02pm,4:00pm - 3:00pm, 4:30pm -6:00pm
- A section has a room.
- A room is identified by its room name, which is alphanumeric.
- A room has a capacity.
- Section enlistment may not exceed the capacity of its room.

### Instructor

- [Calen Legaspi](https://github.com/calen-legaspi)

### Grading Criteria

- Meets Requirements: 20%
- Test-Driven Development: 15%
- Readability: 20%
- Object-Oriented Design Principles: 20%
- Coding Practices: 15%
- Version Control & Continuous Integration Practices: 10%

### Group Members

- [Nadji Tan](https://github.com/Kapatid) (Kapatid)
- [Jeremy Habal](https://github.com/J-Habal)
- [Gabriel Verceles](https://github.com/Koruuin)
- [Jerwin Fabelico](https://github.com/Omni-ssiah)
