/*
The MIT License (MIT)

Copyright (c) 2016 British Broadcasting Corporation.
This software is provided by Lancaster University by arrangement with the BBC.

Permission is hereby granted, free of charge, to any person obtaining a
copy of this software and associated documentation files (the "Software"),
to deal in the Software without restriction, including without limitation
the rights to use, copy, modify, merge, publish, distribute, sublicense,
and/or sell copies of the Software, and to permit persons to whom the
Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
DEALINGS IN THE SOFTWARE.
*/

#include "MicroBit.h"
#include <string>

MicroBit uBit;

char calculate_arrow_orientation(double heading) {
    // correct negatives to be positive
    if (heading < 0) {
        heading += 360;
    }


    if (heading >= 337.5 || heading < 22.5) {
        return '^';  // North
    } else if (heading >= 22.5 && heading < 67.5) {
        return '\\';  // North-East
    } else if (heading >= 67.5 && heading < 112.5) {
        return '<';  // East
    } else if (heading >= 112.5 && heading < 157.5) {
        return '/';  // South-East
    } else if (heading >= 157.5 && heading < 202.5) {
        return 'v';  // South
    } else if (heading >= 202.5 && heading < 247.5) {
        return '\\';  // South-West
    } else if (heading >= 247.5 && heading < 292.5) {
        return '>';  // West
    } else {
        return '/';  // North-West
    }
}

int main()
{
    // Initialise the micro:bit runtime.
    uBit.init();

    //Initialise the compass
    uBit.compass.calibrate();

    // Insert your code here!
    while (true) {
        // Calculate the arrow orientation based on the heading
        char arrow_orientation = calculate_arrow_orientation(uBit.compass.heading());

        //display value in serial
        uBit.serial.puts(to_string(uBit.compass.heading()).c_str());
        uBit.serial.puts("\n\r");

        // Display the arrow
        uBit.display.printChar(arrow_orientation);

        // Sleep for 100 milliseconds
        uBit.sleep(100);
    }

    // If main exits, there may still be other fibers running or registered event handlers etc.
    // Simply release this fiber, which will mean we enter the scheduler. Worse case, we then
    // sit in the idle task forever, in a power efficient sleep.
    release_fiber();
}

