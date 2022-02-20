package interview.utils.utilities;

public class ConsoleProgressUtils {

    public static void progress(String preMessage, int percent){
        System.out.print(preMessage + " " + getProgress(percent) + percent + "% \r");
    }

    public static void progressDone(String preMessage){
        System.out.println(preMessage + " " + getProgress(100) + "Completo!");
    }

    private static String getProgress(int percent){
        if(percent<5){
            return "[                    ] ";
        }else if(percent>5 && percent <10){
            return "[=                   ] ";
        }else if(percent>=10 && percent <15){
            return "[==                  ] ";
        }else if(percent>=15 && percent <20){
            return "[===                 ] ";
        }else if(percent>=20 && percent <25){
            return "[====                ] ";
        }else if(percent>=25 && percent <30){
            return "[=====               ] ";
        }else if(percent>=30 && percent <35){
            return "[======              ] ";
        }else if(percent>=35 && percent <40){
            return "[=======             ] ";
        }else if(percent>=40 && percent <45){
            return "[========            ] ";
        }else if(percent>=45 && percent <50){
            return "[=========           ] ";
        }else if(percent>=50 && percent <55){
            return "[==========          ] ";
        }else if(percent>=55 && percent <60){
            return "[===========         ] ";
        }else if(percent>=60 && percent <65){
            return "[============        ] ";
        }else if(percent>=65 && percent <70){
            return "[=============       ] ";
        }else if(percent>=70 && percent <75){
            return "[==============      ] ";
        }else if(percent>=75 && percent <80){
            return "[===============     ] ";
        }else if(percent>=80 && percent <85){
            return "[================    ] ";
        }else if(percent>=85 && percent <90){
            return "[=================   ] ";
        }else if(percent>=90 && percent <95){
            return "[==================  ] ";
        }else if(percent>=95 && percent <98){
            return "[=================== ] ";
        }else if(percent>=98){
            return "[====================] ";
        }else {
            return "[                    ] ";
        }
    }

}
