package project1.service;
 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
 
import project1.domain.Car;
import project1.domain.MonthlySale;
import project1.domain.Reservation;
import project1.domain.YearlySale;
import project1.persistance.RentCarDAO;
 
//¸ŞÀÎ ¸Ş´ºº° ¾×¼Ç Å¬·¡½º
public class SubMain {
    // RentCarDAO¿¡ Å¬·¡½ºÀÇ °´Ã¼ »ı¼º ¹× ÇÊµå ±¸¼º
    private RentCarDAO dao = new RentCarDAO();
 
    //Á÷·ÄÈ­ ¸Ş¼Òµå »ı¼º(¸ŞÀÎ ¸Ş¼Òµå¿¡¼­ È£ÃâÀ» À§ÇØ)
 
    public void daoSerializables() {
        dao.daoSerializable();
    }
    
    
    // ¼­ºê ¸Ş´º ¿î¿µ ¸Ş¼Òµå
 
    // ·»ÅÍÄ« ver1.0 > ¿¹¾à¸Ş´º
    // 1.¿¹¾à 2.¿¹¾àÃë¼Ò 3.¹İ³³ 0.ÀÌÀü´Ü°è
    // ÀüÀº¹Ì 2018-08-08
    // ±èÀ¯½Å 2018-08-10 -> try-catch, default¹® Ãß°¡
    // ÀüÀº¹Ì 2018-08-10 -> try-catch, default¹® Ãß°¡
    // À¯½Å ÀÛ¾÷ ÈÄ ¼Ò½º °áÇÕÀÌ ¾ÈµÇ¼­ Àº¹Ì°¡ ¾ÈµÈÁÙ ¾Ë°í ¶Ç ÀÛ¾÷ÇÔ;;
    public void mainMenu1(Scanner sc) {
        // ÄÜ¼Ö¿¡ ÀÇÇÑ ¿ÜºÎ ÀÔ·Â ÁØºñ
 
        while (true) {
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("-----------------------------------");
            System.out.println("      ·»ÅÍÄ« ver1.0 > ¿¹¾à¸Ş´º     ");
            System.out.println("-----------------------------------");
            System.out.println("1.¿¹¾à 2.¿¹¾àÃë¼Ò 3.¹İ³³ 0.ÀÌÀü´Ü°è");
            System.out.print("¼±ÅÃ > ");
            try {
                int selectNum = sc.nextInt();
                sc.nextLine();
 
                if (selectNum == 0) break;
 
                switch (selectNum) {
 
                case 1:
                    this.mainMenu1_sub1(sc);
                    break;
                case 2:
                    this.mainMenu1_sub2(sc);
                    break;
                case 3:
                    this.mainMenu1_sub3(sc);
                    break;
                default:
                    System.out.println("´Ù½Ã ¼±ÅÃÇÏ¼¼¿ä (1~3), exit : 0 ");
                    break;
 
                }
            } catch (Exception e) {
                sc.nextLine();
                System.out.println("¢Í¢Í Àß¸øµÈ ¸Ş´º ¼±ÅÃÀÔ´Ï´Ù.");
            }
 
        }
 
    }
 
    // ·»ÅÍÄ« ver1.0 > ¿¹¾à¸Ş´º > ¿¹¾à
    // ÀüÀº¹Ì 2018-08-09 Å×½ºÆ®¿Ï·á ¼öÁ¤»çÇ×ÀÖÀ½
    private void mainMenu1_sub1(Scanner sc) {
        System.out.println();
        System.out.println();
        System.out.println("-----------------------------------");
        System.out.println("  ·»ÅÍÄ« ver1.0 > ¿¹¾à¸Ş´º > ¿¹¾à");
        System.out.println("-----------------------------------");
        try {
            // ´ë¿©½ÃÀÛÀÏ, ´ë¿©¹İ³³ÀÏ ¿ÜºÎÀÔ·Â
            String startDate = "";
            String endDate = "";
 
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
 
            while (true) {
                sf.setLenient(false);
                // ´ë¿©½ÃÀÛÀÏ, ´ë¿©¹İ³³ÀÏ ¿ÜºÎÀÔ·Â
                System.out.print("´ë¿©½ÃÀÛÀÏ(yyyy-MM-dd) > ");
                startDate = sc.nextLine();
                try {
                    sf.parse(startDate);
                    break;
                } catch (Exception e1) {
                    // Æ²¸° ³¯Â¥ÀÎ °æ¿ì¿¹¿Ü¹ß»ı
                    System.out.println("¢º³¯Â¥ Çü½Ä¿¡ ¸Â°Ô ÀÔ·ÂÇÏ¼¼¿ä(yyyy-MM-dd) \n");
                }
            }
 
            while (true) {
                System.out.print("´ë¿©¹İ³³ÀÏ(yyyy-mm-dd) > ");
                endDate = sc.nextLine();
 
                try {
                    sf.parse(endDate);
                    break;
                } catch (Exception e2) {
                    System.out.println("¢º³¯Â¥ Çü½Ä¿¡ ¸Â°Ô ÀÔ·ÂÇÏ¼¼¿ä. (yyyy-MM-dd) \n");
                }
            }
            System.out.println();
            
            // ¿¹¾à°¡´ÉÂ÷·® Ãâ·Â
            this.printBookAble(startDate, endDate);
            System.out.println();
 
            String regExp1 = "(CAR)\\d{3}";
 
            while (true) {
                // Â÷·®¹øÈ£¼±ÅÃ ¹× °³ÀÎÁ¤º¸ ¿ÜºÎÀÔ·Â
                System.out.print("Â÷·®¹øÈ£ ¼±ÅÃ(CARxxx) > ");
                String carId = sc.nextLine();
 
                if (Pattern.matches(regExp1, carId) == false) {
                    System.out.println("Â÷·®¹øÈ£ Çü½Ä¿¡ ¸Â°Ô ÀÔ·ÂÇÏ¼¼¿ä. (CARxxx) \n");
                } else {
                    int rentFee = this.dao.carSearch(carId).getRentFee();
                    String carName = this.dao.carSearch(carId).getCarName();
                    int totalFee = dao.rentMoney(rentFee, startDate, endDate);
 
                    if (carName == null) {
                        System.out.println
                        ("Â÷·®À» Àß¸ø ¼±ÅÃÇÏ¼Ì½À´Ï´Ù. ´Ù½Ã ÀÔ·ÂÇØÁÖ¼¼¿ä.");
                    } else if (carName != null) {
                        System.out.printf
                        ("¼±ÅÃÇÏ½Å [%s]ÀÇ ±İ¾×Àº %,d¿ø ÀÔ´Ï´Ù. ¿¹¾à ÇÏ½Ã°Ú½À´Ï±î? (Y/N)%n"
                                , carName, totalFee);
                        System.out.print("¼±ÅÃ > ");
                        String select = sc.nextLine();
 
                        // ¿¹¾à¹øÈ£ ÀÚµ¿»ı¼º
                        String reservationId = this.dao.autoReservationId();
 
                        String name = "";
                        String phone = "";
                        String licenseId = "";
 
                        // ¿¹¾à »óÅÂ
                        String reservationStatus = "¿¹¾àÈ®ÀÎ";
 
                        // °áÁ¦ÀÏ ¹Ş¾Æ¿À±â
                        String payDate = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
 
                        System.out.println();
                        if (select.equalsIgnoreCase("Y")) {
                            System.out.println("Á¤º¸¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä.");
                            System.out.print("¿¹¾àÀÚ¸í > ");
                            name = sc.nextLine();
 
                            while (true) {
 
                                String regExp2 = "(010)-\\d{3,4}-\\d{4}";
 
                                System.out.print("¿¬¶ôÃ³ > ");
                                phone = sc.nextLine();
 
                                if (Pattern.matches(regExp2, phone) == false) {
                                    System.out.println("¿¬¶ôÃ³ Çü½ÄÀ» ¸ÂÃçÁÖ¼¼¿ä. (010-xxxx-xxxx)");
                                } else {
 
                                    while (true) {
 
                                        String regExp3 = "[¤¡-¤¾°¡-ÆR]{2}\\d{2}-\\d{4}";
                                        System.out.print("¸éÇãÁõ¹øÈ£ (Áö¿ª¸íXX-XXXX) > ");
                                        licenseId = sc.nextLine();
                                        if (Pattern.matches(regExp3, licenseId) == false) {
                                            System.out.println
                                            ("¸éÇãÁõ¹øÈ£ Çü½ÄÀ» ¸ÂÃçÁÖ¼¼¿ä. (Áö¿ª¸íXX-XXXX)");
                                        } else {
 
                                            Reservation r = new Reservation(reservationId, name
                                                    , phone, licenseId,    carId, startDate, endDate
                                                    , payDate, totalFee, reservationStatus);
 
                                            // ListÄÃ·º¼Ç ÀúÀå¼Ò¿¡ ÀúÀå
                                            this.dao.reservationAdd(r);
 
                                            // °áÁ¦ ¿©ºÎ È®ÀÎ ÈÄ,¸Ş½ÃÁö Ãâ·Â
                                            System.out.println();
                                            System.out.println
                                            ("ÀÔ·ÂÀÌ ¿Ï·áµÇ¾ú½À´Ï´Ù. °áÁ¦¸¦ ÁøÇàÇÏ½Ã°Ú½À´Ï±î? (Y/N)");
 
                                            System.out.print("¼±ÅÃ > ");
 
                                            String select2 = sc.nextLine();
 
                                            if (select2.equalsIgnoreCase("Y")) {
 
                                                this.dao.sumPay();
 
                                                System.out.println();
                                                System.out.println("°áÁ¦°¡ ¿Ï·áµÇ¾ú½À´Ï´Ù.");
 
                                                // ¿¹¾à Á¤º¸ Ãâ·Â
                                                this.printResult(reservationId);
 
                                            } else if (select2.equalsIgnoreCase("N")) {
                                                System.out.println("¿¹¾àÀÌ Ãë¼ÒµÇ¾ú½À´Ï´Ù.");
                                            }
                                            break;
                                        }
                                    }
                                    break;
                                }
                            }
 
                        } else if (select.equalsIgnoreCase("N")) {
                            System.out.println("¿¹¾àÀÌ Ãë¼ÒµÇ¾ú½À´Ï´Ù.");
                        } else {
                            System.out.println("Àß¸ø ÀÔ·ÂÇÏ¼Ì½À´Ï´Ù.");
                            break;
                        }
 
                    }
                    break;
                }
            }
 
        } catch (Exception e) {
            System.out.println("Àß¸ø ÀÔ·ÂÇÏ¼Ì½À´Ï´Ù. ´Ù½Ã ¿¹¾àÇØÁÖ¼¼¿ä.");
 
        }
    }
 
    // ·»ÅÍÄ« ver1.0 > ¿¹¾à¸Ş´º > ¿¹¾àÃë¼Ò
    // ÇÔ¼öÈÆ 2018-08-08
    // ÀüÀº¹Ì 2018-08-09 Å×½ºÆ®¿Ï·á ¼öÁ¤»çÇ×ÀÖÀ½
    // ÀüÀº¹Ì 2018-08-10 Å×½ºÆ®¿Ï·á ¼öÁ¤»çÇ×¾øÀ½
    private void mainMenu1_sub2(Scanner sc) {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("-----------------------------------");
        System.out.println("·»ÅÍÄ« ver1.0 > ¿¹¾à¸Ş´º > ¿¹¾àÃë¼Ò");
        System.out.println("-----------------------------------");
        
        // ¿¹¾à¹øÈ£ ¿ÜºÎÀÔ·Â
        while (true) {
            System.out.println("¿¹¾à¹øÈ£¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä.");
            System.out.print("ÀÔ·Â>");
            String regExp4 = "(R)\\d{4}";
            String reservationId = sc.nextLine();
 
            if (Pattern.matches(regExp4, reservationId) == false) {
                System.out.println("Àß¸øµÈ ¿¹¾à¹øÈ£ÀÔ´Ï´Ù. ´Ù½ÃÀÔ·ÂÇØÁÖ¼¼¿ä. (Rxxxx)");
                System.out.println();
            } else {
                System.out.println();
                
                // ¿¹¾àÁ¤º¸Ãâ·Â
                List<Reservation> r = this.printResult(reservationId);
                System.out.println();
 
                // Ãë¼Ò ¿©ºÎ È®ÀÎ
                // ->Yes :Ãë¼ÒÀü¿ë ¸Ş¼Òµå È£Ãâ
                // °á°ú¸Ş½ÃÁö Ãâ·Â
                //¿¹¾à Ãë¼Ò½Ã °áÁ¦±İ¾× 0¿øÀ¸·Î µ¥ÀÌÅÍ ¼öÁ¤ ¸Ş¼Òµå È£Ãâ
                System.out.println("¿¹¾àÀ» Ãë¼ÒÇÏ½Ã°Ú½À´Ï±î? (Y/N)");
                System.out.print("¼±ÅÃ > ");
                String select = sc.nextLine();
                if (select.equalsIgnoreCase("Y")) {
                    this.dao.reservationCancel(r);
                    System.out.println("Ãë¼Ò°¡ ¿Ï·á µÇ¾ú½À´Ï´Ù.");
                } else {
                    System.out.println("¿¹¾àÃë¼Ò°¡ Ãë¼ÒµÇ¾ú½À´Ï´Ù.");
                }
                break;
            }
        }
    }
 
    // ·»ÅÍÄ« ver1.0 > ¿¹¾à¸Ş´º > ¹İ³³
    // ÇÔ¼öÈÆ 2018-08-08
    // ÀüÀº¹Ì20180809 Å×½ºÆ®¿Ï·á - ¼öÁ¤»çÇ×ÀÖÀ½
    // ÀüÀº¹Ì20180810 Å×½ºÆ®¿Ï·á
    private void mainMenu1_sub3(Scanner sc) {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("-------------------------------");
        System.out.println("·»ÅÍÄ« ver1.0 > ¿¹¾à¸Ş´º > ¹İ³³");
        System.out.println("-------------------------------");
        // ¿¹¾à¹øÈ£ ¿ÜºÎÀÔ·Â
        while (true) {
            System.out.println("¿¹¾à¹øÈ£¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä.");
            System.out.print("ÀÔ·Â>");
            String regExp4 = "(R)\\d{4}";
            String reservationId = sc.nextLine();
 
            if (Pattern.matches(regExp4, reservationId) == false) {
                System.out.println("Àß¸øµÈ ¿¹¾à¹øÈ£ÀÔ´Ï´Ù. ´Ù½ÃÀÔ·ÂÇØÁÖ¼¼¿ä. (Rxxxx)");
                System.out.println();
            } else {
 
                System.out.println();
                // ¿¹¾àÁ¤º¸Ãâ·Â
                List<Reservation> r = this.printResult(reservationId);
                System.out.println();
 
                // ¹İ³³ ¿©ºÎ È®ÀÎ
                // ->Yes :Ãë¼ÒÀü¿ë ¸Ş¼Òµå È£Ãâ
                // °á°ú¸Ş½ÃÁö Ãâ·Â
                System.out.println("Â÷·®À» ¹İ³³ÇÏ½Ã°Ú½À´Ï±î? (Y/N) ");
                System.out.print("¼±ÅÃ > ");
                String select = sc.nextLine();
                if (select.equalsIgnoreCase("Y")) {
                    this.dao.returnCar(r);
                    System.out.println("¹İ³³ÀÌ ¿Ï·á µÇ¾ú½À´Ï´Ù.");
                } else {
                    System.out.println("Â÷·®¹İ³³ÀÌ Ãë¼ÒµÇ¾ú½À´Ï´Ù.");
                }
                break;
            }
        }
    }
 
    /********************************************************************/
 
    // ·»ÅÍÄ« ver1.0 > °ü¸®¸Ş´º
    // 1.¿¹¾à°ü¸® 2.Â÷·®°ü¸® 3.¸ÅÃâ°ü¸® 0.ÀÌÀü´Ü°è
    public void mainMenu2(Scanner sc) {
        // ÄÜ¼Ö¿¡ ÀÇÇÑ ¿ÜºÎ ÀÔ·Â ÁØºñ
        // ±èÀ¯½Å 2018-08-10 try-catch¹® Ãß°¡
        while (true) {
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("-------------------------------------------");
            System.out.println("         ·»ÅÍÄ« ver1.0 > °ü¸®¸Ş´º          ");
            System.out.println("-------------------------------------------");
            System.out.println("1.¿¹¾à°ü¸® 2.Â÷·®°ü¸® 3.¸ÅÃâ°ü¸® 0.ÀÌÀü´Ü°è");
            System.out.print("¼±ÅÃ > ");
            try {
                int selectNum = sc.nextInt();
                sc.nextLine();
 
                if (selectNum == 0)
                    break;
 
                switch (selectNum) {
 
                case 1:
                    this.dao.autoChangeStatus();
                    this.mainMenu2_sub1(sc);
                    break;
                case 2:
                    this.dao.autoChangeStatus();
                    this.mainMenu2_sub2();
                    break;
                case 3:
                    this.dao.autoChangeStatus();
                    this.mainMenu2_sub3(sc);
                    break;
 
                default:
                    System.out.println("´Ù½Ã ¼±ÅÃÇÏ¼¼¿ä (1~3), exit : 0 ");
                    break;
 
                }
            } catch (Exception e) {
                sc.nextLine();
                System.out.println("Àß¸øµÈ ÀÔ·ÂÀÔ´Ï´Ù.");
            }
        }
 
    }
 
    // ·»ÅÍÄ« ver1.0 > °ü¸®¸Ş´º > ¿¹¾à°ü¸®
    // 1.Æ¯Á¤¿ù 2.Æ¯Á¤ÀÏ 3.¿¹¾à¹øÈ£ 4.¿¹¾àÀÚº° 5.Â÷·®º° 6.ÀüÃ¼Ãâ·Â 0.ÀÌÀü´Ü°è
    // ÀÌ¸íÀç 2018-08-09
    // ±èÀ¯½Å 2018-08-10 try-catch¹® Ãß°¡
    private void mainMenu2_sub1(Scanner sc) {
        // ÄÜ¼Ö¿¡ ÀÇÇÑ ¿ÜºÎ ÀÔ·Â ÁØºñ
        while (true) {
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("-----------------------------------");
            System.out.println("·»ÅÍÄ« ver1.0 > °ü¸®¸Ş´º > ¿¹¾à°ü¸®");
            System.out.println("-----------------------------------");
            System.out.println("1.Æ¯Á¤¿ù 2.Æ¯Á¤ÀÏ 3.¿¹¾à¹øÈ£ 4.¿¹¾àÀÚº° 5.Â÷·®º° 6.ÀüÃ¼Ãâ·Â 0.ÀÌÀü´Ü°è");
            System.out.print("¼±ÅÃ > ");
            try {
                int selectNum = sc.nextInt();
                sc.nextLine();
 
                if (selectNum == 0)
                    break;
                switch (selectNum) {
                case 1:
                    this.mainMenu2_sub1_sub1(sc);
                    break;
                case 2:
                    this.mainMenu2_sub1_sub2(sc);
                    break;
                case 3:
                    this.mainMenu2_sub1_sub3(sc);
                    break;
                case 4:
                    this.mainMenu2_sub1_sub4(sc);
                    break;
                case 5:
                    this.mainMenu2_sub1_sub5(sc);
                    break;
                case 6:
                    this.mainMenu2_sub1_sub6();
                    break;
                default:
                    System.out.println("´Ù½Ã ¼±ÅÃÇÏ¼¼¿ä (1~6), exit : 0 ");
                    break;
 
                }
            } catch (Exception e) {
                sc.nextLine();
                System.out.println("Àß¸øµÈ ÀÔ·ÂÀÔ´Ï´Ù.");
            }
        }
    }
 
    // ·»ÅÍÄ« ver1.0 > °ü¸®¸Ş´º > ¿¹¾à°ü¸® > Æ¯Á¤¿ù
    // ÀÌ¸íÀç 2018-08-09
    private void mainMenu2_sub1_sub1(Scanner sc) {
 
        // ¿ÜºÎÀÔ·ÂÀ¸·Î Æ¯Á¤¿ùÀ» ¹Ş¾Æ¿Â´Ù.
        // -> ¿¹½Ã)³¯Â¥(YYYY-MM)¸¦ ÀÔ·ÂÇÏ¼¼¿ä
        // -> 2018-08
        // Á¤·Ä ±âÁØÀº ¿¹¾à¹øÈ£·Î ÇÑ´Ù.
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("--------------------------------------------");
        System.out.println("·»ÅÍÄ« ver1.0 > °ü¸®¸Ş´º > ¿¹¾à°ü¸® > Æ¯Á¤¿ù");
        System.out.println("--------------------------------------------");
 
        // Æ¯Á¤¿ù¿¡ ÇØ´çµÇ´Â ÀÚ·á °Ë»ö
        // ------------------------------------
        // try-catch OR Pattern Ãß°¡ ÇÒ°Í!!!!!!(Áú¹®)
        // ------------------------------------
        System.out.print("³¯Â¥(YYYY-MM)¸¦ ÀÔ·ÂÇÏ¼¼¿ä> ");
        String month = sc.nextLine();
        
        // °Ë»ö ±âÁØÀº ´ë¿©½ÃÀÛÀÏ·Î ÇÑ´Ù.
        // »óÅÂ °ªÀÇ ±âÁØÀº ¿À´Ã ³¯Â¥¸¦ ±âÁØÀ¸·Î ÇÑ´Ù.
        // °á°ú Ãâ·Â -> Ãâ·Â ¸Ş¼Òµå È£Ãâ
        this.printReservationStatus(this.dao.reservationSearch(month), "month");
 
    }
 
    // ·»ÅÍÄ« ver1.0 > °ü¸®¸Ş´º > ¿¹¾à°ü¸® > Æ¯Á¤ÀÏ
    // ÀÌ¸íÀç 2018-08-09
    // ±èÀ¯½Å 2018-08-10 try-catch¹® ÀÔ·Â
    private void mainMenu2_sub1_sub2(Scanner sc) {
 
        // ¿ÜºÎÀÔ·ÂÀ¸·Î Æ¯Á¤ÀÏÀ» ¹Ş¾Æ¿Â´Ù.
        // -> ¿¹½Ã)³¯Â¥(YYYY-MM-dd)¸¦ ÀÔ·ÂÇÏ¼¼¿ä
        // -> 2018-08-03
        // Á¤·Ä ±âÁØÀº ¿¹¾à¹øÈ£·Î ÇÑ´Ù.
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("--------------------------------------------");
        System.out.println("·»ÅÍÄ« ver1.0 > °ü¸®¸Ş´º > ¿¹¾à°ü¸® > Æ¯Á¤ÀÏ");
        System.out.println("--------------------------------------------");
 
        // Æ¯Á¤ÀÏ¿¡ ÇØ´çµÇ´Â ÀÚ·á °Ë»ö
         String date = "";
          while (true) {
             SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
             sf.setLenient(false);
             // Æ¯Á¤ÀÏ¿¡ ÇØ´çµÇ´Â ÀÚ·á °Ë»ö
             System.out.print("³¯Â¥(YYYY-MM-dd)¸¦ ÀÔ·ÂÇÏ¼¼¿ä> ");
             date = sc.nextLine();
             try {
                sf.parse(date);
                break;
             } catch (ParseException e1) {
                // Æ²¸° ³¯Â¥ÀÎ °æ¿ì¿¹¿Ü¹ß»ı
                System.out.println("³¯Â¥ Çü½Ä¿¡ ¸Â°Ô ÀÔ·ÂÇÏ¼¼¿ä(yyyy-MM-dd)\n");
             }
          }
        // ÇØ´ç ÀÚ·áµéÀº ¿¹¾à±â°£ Áß¿¡ ÇØ´çµÇ¸é Ãâ·ÂµÈ´Ù.
        // »óÅÂ °ªÀÇ ±âÁØÀº ¿ÜºÎÀÔ·ÂÀ» ÅëÇØ ÀÔ·Â ¹ŞÀº ³¯Â¥¸¦ ±âÁØÀ¸·Î ÇÑ´Ù.
        // °á°ú Ãâ·Â -> Ãâ·Â ¸Ş¼Òµå È£Ãâ
        this.printReservationStatus(this.dao.reservationSearch(date), "date");
 
    }
 
    // ·»ÅÍÄ« ver1.0 > °ü¸®¸Ş´º > ¿¹¾à°ü¸® > ¿¹¾à¹øÈ£
    // ÀÌ¸íÀç 2018-08-09
    private void mainMenu2_sub1_sub3(Scanner sc) {
        // ¿ÜºÎÀÔ·ÂÀ¸·Î ¿¹¾à¹øÈ£¸¦ ¹Ş¾Æ¿Â´Ù.
        // -> ¿¹½Ã)¿¹¾à¹øÈ£¸¦ ÀÔ·ÂÇÏ¼¼¿ä.
        // -> R0003
        // Á¤·Ä ±âÁØÀº ¿¹¾à¹øÈ£·Î ÇÑ´Ù.
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("----------------------------------------------");
        System.out.println("·»ÅÍÄ« ver1.0 > °ü¸®¸Ş´º > ¿¹¾à°ü¸® > ¿¹¾à¹øÈ£");
        System.out.println("----------------------------------------------");
 
        // ¿¹¾à¹øÈ£¿¡ ÇØ´çµÇ´Â ÀÚ·á °Ë»ö
        // ------------------------------------
        // try-catch OR Pattern Ãß°¡ ¿Ï·á 20180813 ÇÔ¼öÈÆ
        // ------------------------------------
        while (true) {
            System.out.print("¿¹¾à¹øÈ£¸¦ ÀÔ·ÂÇÏ¼¼¿ä> ");
            String reservationId = sc.nextLine();
            String regExp = "(R)\\d{4}";
            if (Pattern.matches(regExp, reservationId) == false) {
                System.out.println("Àß¸øµÈ ¿¹¾à¹øÈ£ÀÔ´Ï´Ù. ´Ù½ÃÀÔ·ÂÇØÁÖ¼¼¿ä. (Rxxxx)");
            } else {
                // »óÅÂ °ªÀÇ ±âÁØÀº ¿À´Ã ³¯Â¥¸¦ ±âÁØÀ¸·Î ÇÑ´Ù.
                // °á°ú Ãâ·Â -> Ãâ·Â ¸Ş¼Òµå È£Ãâ
                this.printReservationStatus(this.dao.reservationSearch(reservationId), "reservationId");
                break;
            }
        }
    }
 
    // ·»ÅÍÄ« ver1.0 > °ü¸®¸Ş´º > ¿¹¾à°ü¸® > ¿¹¾àÀÚ
    // ÀÌ¸íÀç 2018-08-09
    private void mainMenu2_sub1_sub4(Scanner sc) {
 
        // ¿ÜºÎÀÔ·ÂÀ¸·Î ÀÌ¸§°ú ¿¬¶ôÃ³¸¦ ¹Ş¾Æ¿Â´Ù.
        // -> ¿¹½Ã)ÀÌ¸§>È«±æµ¿
        // -> ¿¬¶ôÃ³>010-1234-1234
        // Á¤·Ä ±âÁØÀº ¿¹¾à¹øÈ£·Î ÇÑ´Ù.
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("--------------------------------------------");
        System.out.println("·»ÅÍÄ« ver1.0 > °ü¸®¸Ş´º > ¿¹¾à°ü¸® > ¿¹¾àÀÚ");
        System.out.println("--------------------------------------------");
 
        // ¿¹¾àÀÚ¿¡ ÇØ´çµÇ´Â ÀÚ·á °Ë»ö
        System.out.print("ÀÌ¸§À» ÀÔ·ÂÇÏ¼¼¿ä> ");
        String name = sc.nextLine();
        
        // ------------------------------------
        // try-catch OR Pattern Ãß°¡ ¿Ï·á 20180813 ÇÔ¼öÈÆ
        // ------------------------------------
        while (true) {
            System.out.print("¿¬¶ôÃ³¸¦ ÀÔ·ÂÇÏ¼¼¿ä> ");
            String phone = sc.nextLine();
            String regExp = "(010)-\\d{3,4}-\\d{4}";
            if (Pattern.matches(regExp, phone) == false) {
                System.out.println("¿¬¶ôÃ³ Çü½ÄÀ» ¸ÂÃçÁÖ¼¼¿ä. (010-xxxx-xxxx)");
            } else {
                // »óÅÂ °ªÀÇ ±âÁØÀº ¿À´Ã ³¯Â¥¸¦ ±âÁØÀ¸·Î ÇÑ´Ù.
                // °á°ú Ãâ·Â -> Ãâ·Â ¸Ş¼Òµå È£Ãâ
                this.printReservationStatus(this.dao.reservationSearch(name, phone), "name");
                break;
            }
        }
    }
 
    // ·»ÅÍÄ« ver1.0 > °ü¸®¸Ş´º > ¿¹¾à°ü¸® > Â÷·®º°
    // ÀÌ¸íÀç 2018-08-09
    private void mainMenu2_sub1_sub5(Scanner sc) {
 
        // ¿ÜºÎÀÔ·ÂÀ¸·Î Â÷·® °íÀ¯ °ªÀ» ¹Ş¾Æ¿Â´Ù.
        // -> ¿¹½Ã)CAR003
        // Á¤·Ä ±âÁØÀº ¿¹¾à¹øÈ£·Î ÇÑ´Ù.
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("--------------------------------------------");
        System.out.println("·»ÅÍÄ« ver1.0 > °ü¸®¸Ş´º > ¿¹¾à°ü¸® > Â÷·®º°");
        System.out.println("--------------------------------------------");
        // Æ¯Á¤Â÷·®¹øÈ£¿¡ ÇØ´çµÇ´Â ÀÚ·á °Ë»ö.
        // ------------------------------------
        // try-catch OR Pattern Ãß°¡ ¿Ï·á 20180813 ÇÔ¼öÈÆ
        // ------------------------------------
        while (true) {
            System.out.print("Â÷·®¹øÈ£¸¦ ÀÔ·ÂÇÏ¼¼¿ä> ");
            String carId = sc.nextLine();
            String regExp = "(CAR)\\d{3}";
            if (Pattern.matches(regExp, carId) == false) {
                System.out.println("Â÷·®¹øÈ£ Çü½Ä¿¡ ¸Â°Ô ÀÔ·ÂÇÏ¼¼¿ä. (CARxxx) \n");
            } else {
                // »óÅÂ °ªÀÇ ±âÁØÀº ¿À´Ã ³¯Â¥¸¦ ±âÁØÀ¸·Î ÇÑ´Ù.
                // °á°ú Ãâ·Â -> Ãâ·Â ¸Ş¼Òµå È£Ãâ
                this.printReservationStatus(this.dao.reservationSearch(carId), "carId");
                break;
            }
        }
    }
 
    // ·»ÅÍÄ« ver1.0 > °ü¸®¸Ş´º > ¿¹¾à°ü¸® > ÀüÃ¼Ãâ·Â
    // ¿Áº¸¶ó 2018.08.08
    private void mainMenu2_sub1_sub6() {
        System.out.println("****************************");
        System.out.println("ÀüÃ¼ ¿¹¾à ÇöÈ²");
        System.out.println("¿¹¾à¹øÈ£ / ¿¹¾àÀÚ¸í / ¿¬¶ôÃ³ / Â÷·®¹øÈ£ / Â÷Á¾ / ¿¹¾à±â°£ / »óÅÂ");
        // ÀüÃ¼ ¿¹¾à ÇöÈ² Ãâ·Â -> Ãâ·Â ¸Ş¼Òµå È£Ãâ
        // »óÅÂ °ªÀÇ ±âÁØÀº ¿À´Ã ³¯Â¥¸¦ ±âÁØÀ¸·Î ÇÑ´Ù.
        List<Reservation> temp = this.dao.reservationList();
        for (Reservation r : temp) {
            System.out.println(r);
        }
        System.out.println("****************************");
    }
 
    /********************************************************************/
 
    // ·»ÅÍÄ« ver1.0 > °ü¸®¸Ş´º > Â÷·®°ü¸®
    // ¿Áº¸¶ó 2018.08.08 / ÀüÃ¼ Â÷·® º¸À¯ ÇöÈ² Ãâ·Â
    private void mainMenu2_sub2() {
        // ÀüÃ¼ Â÷·® º¸À¯ÇöÈ² Ãâ·Â (toString)
        System.out.println("****************************");
        System.out.println("ÀüÃ¼ Â÷·® º¸À¯ ÇöÈ²");
        System.out.println("Â÷·®¹øÈ£ /  Â÷·®  /  Å©±â  / Å¾½ÂÀÎ¿ø / 1ÀÏ ·»Æ®ºñ");
 
        List<Car> temp = this.dao.carList();
        for (Car c : temp) {
            System.out.println(c);
        }
        System.out.println("****************************");
    }
 
    /********************************************************************/
 
    // ·»ÅÍÄ« ver1.0 > °ü¸®¸Ş´º > ¸ÅÃâ°ü¸®
    // 1.Â÷Á¾º° ¿¬¸ÅÃâ ÇöÈ² 2.¿ùº° ¸ÅÃâ ÇöÈ² 0. ÀÌÀü´Ü°è
    // ±èÀ¯½Å 2018-08-10 try-catch¹® Ãß°¡
    // ÀüÀº¹Ì 20180810 try-catch Ãß°¡
    // ÀÌ°Å ¶ÇÇÑ ¼Ò½º ÅëÇÕÀÌ ¾ÈµÇ¾î Áßº¹À¸·Î ÇÏ°ÔµÊ;;;
    private void mainMenu2_sub3(Scanner sc) {
        // ÄÜ¼Ö¿¡ ÀÇÇÑ ¿ÜºÎ ÀÔ·Â ÁØºñ
        while (true) {
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("-------------------------------------------------");
            System.out.println("       ·»ÅÍÄ« ver1.0 > °ü¸®¸Ş´º > ¸ÅÃâ°ü¸®       ");
            System.out.println("-------------------------------------------------");
            System.out.println("1.Â÷Á¾º° ¿¬¸ÅÃâ ÇöÈ² 2.¿ùº° ¸ÅÃâ ÇöÈ² 0. ÀÌÀü´Ü°è");
 
            System.out.print("¼±ÅÃ > ");
            try {
                int selectNum = sc.nextInt();
                sc.nextLine();
 
                if (selectNum == 0)
                    break;
 
                switch (selectNum) {
                case 1:
                    this.mainMenu2_sub3_sub1(sc);
                    break;
                case 2:
                    this.mainMenu2_sub3_sub2(sc);
                    break;
                default:
                    System.out.println("´Ù½Ã ¼±ÅÃÇÏ¼¼¿ä (1~2), exit : 0 ");
                    break;
 
                }
            } catch (Exception e) {
                sc.nextLine();
                System.out.println("Àß¸øµÈ ÀÔ·ÂÀÔ´Ï´Ù.");
            }
        }
    }
 
    // ·»ÅÍÄ« ver1.0 > °ü¸®¸Ş´º > ¸ÅÃâ°ü¸® > Â÷Á¾º° ¿¬¸ÅÃâ ¸ÅÃâ
       // ±èÀ¯½Å 2018-08-08
       private void mainMenu2_sub3_sub1(Scanner sc) {
 
          // °Ë»öÇÒ ¿¬µµ ¿ÜºÎÀÔ·Â
          System.out.print("°Ë»ö³âµµ(YYYY)>");
          String year = sc.nextLine();
 
          // °Ë»ö¿¬µµÀÇ Â÷Á¾º° ¿¬¸ÅÃâ ÇöÈ² Ãâ·Â¸Ş¼Òµå È£Ãâ
          List<YearlySale> sales = dao.annualSalesSearch(year);
          int count = 0;
          int total = 0;
          System.out.println("***************************");
          System.out.println("***" + year + "³âµµ ÃÑ ¸ÅÃâÇöÈ²***");
          System.out.println("Â÷Á¾ ¸ñ·Ï / °Ç¼ö / ´©Àû ±İ¾×");
          for (YearlySale s : sales) {
             System.out.println(s);
             count += s.getCount();
             total += s.getTotalPay();
          }
          System.out.println("===========================");
          System.out.println("ÃÑ °Ç¼ö / ÃÑ ´©Àû ±İ¾×");
          System.out.printf("%s°Ç / %,d¿ø%n", count, total);
          System.out.println("***************************");
       }
 
       // ·»ÅÍÄ« ver1.0 > °ü¸®¸Ş´º > ¸ÅÃâ°ü¸®-¿ùº°¸ÅÃâ
       // 2018-08-09 À¯½Å º¸¶ó
       private void mainMenu2_sub3_sub2(Scanner sc) {
          // °Ë»öÇÒ ³â¿ù ¿ÜºÎÀÔ·Â
          System.out.print("³¯Â¥(YYYY-MM)>");
          String month = sc.nextLine();
          List<MonthlySale> sales = dao.monthlySalesSearch(month);
 
          Collections.sort(sales, new Comparator<MonthlySale>() {
             @Override
             public int compare(MonthlySale o1, MonthlySale o2) {
                return o1.getMonth().compareTo(o2.getMonth());
             }
          });
 
          int count = 0;
          int total = 0;
          System.out.println("***************************");
          System.out.println("³¯Â¥ / °Ç¼ö / ´©Àû ±İ¾×");
          for (MonthlySale s : sales) {
             System.out.println(s);
             count += s.getCount();
             total += s.getTotalPay();
          }
          System.out.println("===========================");
          System.out.println("ÃÑ °Ç¼ö / ÃÑ ´©Àû ±İ¾×");
          System.out.printf("%s°Ç / %,d¿ø%n", count, total);
          System.out.println("***************************");
       }
 
    /********************************************************************/
 
    // ¿¹¾à,¿¹¾àÃë¼Ò,¹İ³³ Ãâ·Â Àü¿ë ¸Ş¼Òµå
    // 2018-08-09 Àº¹Ì
    private List<Reservation> printResult(String reservationId) {
        // ¿¹¾à¹øÈ£ / ¿¹¾àÀÚ¸í / ¿¬¶ôÃ³ / Â÷Á¾ / ¿¹¾à±â°£ / °áÁ¦ÀÏ / °áÁ¦±İ¾×
        List<Reservation> temp = dao.reservationList(reservationId);
 
        Reservation reservation = null;
 
        for (Reservation r : temp) {
            reservation = r;
        }
        
        System.out.println("*******************************************"
                + "***********************");
        System.out.println("¿¹¾à¹øÈ£ / ¿¹¾àÀÚ¸í / ¿¬¶ôÃ³ / Â÷Á¾ / ¿¹¾à±â°£ "
                + "/ °áÁ¦ÀÏ / °áÁ¦±İ¾×");
        System.out.printf("%s/ %s/ %s/ %s/ %s ~ %s/ %s/ %,d¿ø %n"
                , reservation.getReservationId(), reservation.getName()
                , reservation.getPhone()
                , dao.carSearch(reservation.getCarId()).getCarName()
                , reservation.getStartDate(),
                reservation.getEndDate(), reservation.getPayDate()
                , reservation.getPay());
        System.out.println("********************************************"
                + "**********************");
        return temp;
    }
 
    // ¿¹¾à°¡´ÉÂ÷·® Ãâ·Â
    private void printBookAble(String sDate, String eDate) {
        // car Å¬·¡½º¿¡¼­ toString Ãâ·Â
        System.out.println("***************************");
        System.out.println("[¿¹¾à°¡´ÉÂ÷·®]");
        System.out.println("Â÷·®¹øÈ£ / Â÷Á¾ / Å©±â / Å¾½ÂÀÎ¿ø / 1ÀÏ·»Æ®ºñ");
 
        // °¡´ÉÂ÷·® Ãâ·Â
        for(Car c : dao.reservationCarAble(sDate, eDate)) {
             System.out.println(c);
          }
        System.out.println("***************************");
    }
 
    // ¿¹¾à ÇöÈ² Ãâ·Â
    // ÀÌ¸íÀç 18-08-09
    private void printReservationStatus(List<Reservation> reservation, String key) {
        // ¿¹¾à¹øÈ£ / ¿¹¾àÀÚ¸í / ¿¬¶ôÃ³ / Â÷·®¹øÈ£ / Â÷Á¾ / ¿¹¾à±â°£ / »óÅÂ
        if (key.equals("date")) {
            System.out.println();
            System.out.println("¢º¿¹¾à ÇöÈ²¢¸");
            System.out.println("*****************************************************************************");
            System.out.println("¿¹¾à¹øÈ£ / ¿¹¾àÀÚ¸í / ¿¬¶ôÃ³ / Â÷·®¹øÈ£ / Â÷Á¾ /   ¿¹¾à±â°£ /     »óÅÂ(°Ë»ö ±âÁØ)");
            for (Reservation r : reservation) {
                System.out.printf("%s   / %s / %s / %s / %s / %s ~ %s / %s%n", r.getReservationId(), r.getName(),
                        r.getPhone(), r.getCarId(), dao.carSearch(r.getCarId()).getCarName(), r.getStartDate(),
                        r.getEndDate(), r.getReservationStatus());
            }
            System.out.println("*****************************************************************************");
 
        } else {
            System.out.println();
            System.out.println("¢º¿¹¾à ÇöÈ²¢¸");
            System.out.println("*****************************************************************************");
            System.out.println("¿¹¾à¹øÈ£ / ¿¹¾àÀÚ¸í / ¿¬¶ôÃ³ / Â÷·®¹øÈ£ / Â÷Á¾ /   ¿¹¾à±â°£ /     »óÅÂ(¿À´Ã ±âÁØ)");
            for (Reservation r : reservation) {
                System.out.printf("%s   / %s / %s / %s / %s / %s ~ %s / %s%n", r.getReservationId(), r.getName(),
                        r.getPhone(), r.getCarId(), dao.carSearch(r.getCarId()).getCarName(), r.getStartDate(),
                        r.getEndDate(), r.getReservationStatus());
            }
            System.out.println("*****************************************************************************");
        }
 
    }
    /********************************************************************/
 
}