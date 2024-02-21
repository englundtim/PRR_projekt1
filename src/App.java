import java.util.Random;
import java.util.Scanner;


public class App {

    static int playerHP = 100;
    static int enemyHP = 100;
    static boolean potion = false;
    static boolean enemyPotion = false;
    static int roundNumber = 1;

    public static void main(String[] args) throws Exception {
        Scanner tb = new Scanner(System.in);
        Random rand = new Random();
        boolean play = true;
        boolean ongoingGame = false;
        int rand_hit = 0;
        int rand_line = 0;
        int dmgTot = 0;
        
        //Startmeny
        System.out.println("------------------------------------------------ \n ------------ULTIMATE NTI FIGHTER SIM------------ \n ------------------------------------------------");

        //Vill spelaren spela?
        while (play == true) {
            System.out.println("Vill du starta ett game? (ja/nej)");
            String consent = tb.nextLine();
            if (consent.equalsIgnoreCase("ja")) {
                //kör programmet
                consent = "";
                ongoingGame = true;
                roundNumber = 1;
                playerHP = 100;
                enemyHP = 100;
                intro();
                while (ongoingGame==true) {
                    fightSequence(tb, rand, rand_hit, rand_line, dmgTot);
                    enemyFightSequence(tb, rand, rand_hit, dmgTot);
                    System.out.println("Mågverts liv är nu:   "+enemyHP+"HP");
                    if (enemyHP <=0){
                        System.out.println("Grattis, du har besegrat Mågvert!\n");
                        ongoingGame = false;
                    }
                    else if (playerHP <=0) {
                        System.out.println("Du dog mot den starka Mågvert, han skrattar åt din förlust");
                        ongoingGame = false;
                    }
                }
                
            }
            else if(consent.equalsIgnoreCase("nej")){
                System.out.println("ok ha det bra");
                System.exit(0);
            }
            else if (consent.equals("")) {
                //Skicka tillbaka spelaren till huvudmenyn
            }
            else{
                System.out.println("Vad menade du?");
            }
        }
        //En runda

        //Motståndarens runda

        // array för lines inför runda och alla attackmeddelanden, 2d
        //berserk attack med rekursion

    }

    static void intro(){
        System.out.println("Du ska möta det värsta världen har att erbjuda, du möter den starkaste av de starkaste. \n Den grymma... \n Den modige... \n MÅGVERT \n ");
    }
    static void fightSequence(Scanner tb, Random rand, int rand_hit, int rand_line, int dmgTot){
        int action = 0;
       
        String actionRead;
        System.out.println("\nRunda "+roundNumber);
        roundNumber++;

        //hp
        System.out.println("Spelarens HP:   "+playerHP);

        final int LIGHT_ATTACK = 1;
        final int HEAVY_ATTACK =2;
        final int COMBO_ATTACK = 3;
        final int POTION =4;
        
        //läs in action från användaren och verifiera giltig typ
        while (action == 0) {
            System.out.println("Vad vill du göra? \n 1. lätt slag (80% träffchans)\n 2. Kraftfullt slag (50% träffchans)\n 3. Comboattack (3*60% träffchans) \n 4. Styrkebrygd");
            actionRead = tb.nextLine();
            try{
               action = Integer.parseInt(actionRead);
            }
            catch(Exception e){
                System.out.println("Välj en siffra ditt orpon");
                action = 0;
            }
            if(action<1 || action>4){
                System.out.println("Välj val 1, 2, 3 eller 4");
                action = 0;
            }
        }
        
        switch (action) {
            case LIGHT_ATTACK:
                //Lätt attack
                rand_line = rand.nextInt(2);
                switch (rand_line) {
                    case 0:
                        System.out.println("Du samlar styrka och svingar ditt svärd mot Mågvert");
                        break;
                    
                    case 1:
                        System.out.println("Du stirrar in i Mågverts ögon, och han kollar in i dina. Med dina giganormiska armar lyfter du ditt svärd och slår mot din kära");
                        break;
                }
                rand_hit = rand.nextInt(100);
                if (rand_hit < 20) {
                    System.out.println("Trots att du försökte ditt bästa missade du din motståndare avsevärt");
                }
                else{
                    if (potion == true) {
                        potion = false;
                        enemyHP-=20;
                        System.out.println("Du träffar Mågvärt i bröstet och gör "+20+" skada");
                    }
                    else{
                        enemyHP-=15;
                        System.out.println("Du träffar Mågvärt i bröstet och gör "+15+" skada");
                    }
                }
                
                break;
            case HEAVY_ATTACK:
                rand_line = rand.nextInt(2);
                switch (rand_line) {
                    case 0:
                        System.out.println("Du samlar styrka och svingar ditt svärd mot Mågvert");
                        break;
                    
                    case 1:
                        System.out.println("Du stirrar in i Mågverts ögon, och han kollar in i dina. Med dina giganormiska armar lyfter du ditt svärd och slår mot din kära");
                        break;
                }
                rand_hit = rand.nextInt(100);
                if (rand_hit < 50) {
                    System.out.println("Trots att du försökte ditt bästa missade du din motståndare avsevärt");
                }
                else{
                    if (potion == true) {
                        potion = false;
                        enemyHP-=50;
                        System.out.println("Du träffar Mågvärt i bröstet och gör "+50+" skada");
                    }
                    else{
                        enemyHP-=35;
                        System.out.println("Du träffar Mågvärt i bröstet och gör "+35+" skada");
                    }
                }
                break;

            case COMBO_ATTACK:
                System.out.println("Du spinner upp hela kroppen i förberedelse för din specialattack");
                for (int i = 0; i < 3; i++) {
                    rand_hit = rand.nextInt(100);
                    if (rand_hit < 30) {
                        System.out.println("Slag "+(i+1)+" missar");
                    }
                    else{
                        if (potion == true) {
                            System.out.println("Slag "+(i+1)+" träffar");
                            dmgTot+=15;
                        }
                        else{
                            System.out.println("Slag"+(i+1)+" Träffar");
                            dmgTot+=10;
                        }
                    }

                }
                potion = false;
                System.out.println("Du gjorde totalt "+dmgTot+" skada");
                enemyHP-=dmgTot;
                break;

            case POTION:
                potion = true;
                System.out.println("Du tog lite dunderhonung som gör nästa attack starkare");
                break;

            default:
                System.out.println("Välj val 1, 2 eller 3");
                break;

        }

    }
    static void enemyFightSequence(Scanner tb, Random rand, int rand_hit, int dmgTot){
        int enemyAction;
        if (enemyPotion==false) {
            enemyAction = rand.nextInt(4);
            switch (enemyAction) {
                case 0:
                    System.out.println("\n Mågvert slår dig snabbt");
                    rand_hit=rand.nextInt(100);
                    if (rand_hit < 20) {
                        System.out.println("...Men han missar");
                    }
                    else{
                    System.out.println("du slaktad");
                    playerHP-=20;
                    }
                    break;
                
                case 1:
                    System.out.println("\n Mågvert går för ett starkt slag");
                    rand_hit = rand.nextInt(100);
                    if (rand_hit < 50) {
                        System.out.println("Mågvert missar sitt slag");
                    }
                    else{
                        playerHP-=35;
                        System.out.println("Mågvert träffar dig och gör "+35+" skada");
                    }
                    break;

                case 2:
                    System.out.println("\n Mågvert utför en comboattack");
                    for (int i = 0; i < 3; i++) {
                        rand_hit = rand.nextInt(100);
                        if (rand_hit < 30) {
                            System.out.println("Slag "+(i+1)+" missar");
                        }
                        else{
                            System.out.println("Slag"+(i+1)+" Träffar");
                            dmgTot+=10;
                        }
    
                    }
                    System.out.println("Mågvert gjorde totalt "+dmgTot+" skada");
                    playerHP-=dmgTot;
                    break;
                case 3:
                    System.out.println("\n Mågvert tar en trenspruta");
                    enemyPotion=true;
                    break;
            }
        }
        else{
            enemyAction = rand.nextInt(3);
            switch (enemyAction) {
                case 0:
                    System.out.println("\n Mågvert slår dig med ett försärkt lättslag");
                    rand_hit=rand.nextInt(100);
                    if (rand_hit < 20) {
                        System.out.println("...Men han missar");
                    }
                    else{
                    System.out.println("du slaktad");
                    playerHP-=20;
                    }
                    break;
                
                case 1:
                    System.out.println("\n Mågvert går för ett försärkt starkt slag");
                    rand_hit = rand.nextInt(100);
                    if (rand_hit < 50) {
                        System.out.println("Mågvert missar sitt slag");
                    }
                    else{
                        playerHP-=35;
                        System.out.println("Mågvert träffar dig och gör "+35+" skada");
                    }
                    break;
                    
                case 2:
                    System.out.println("\n Mågvert utför en förstärkt comboattack");
                    for (int i = 0; i < 3; i++) {
                        rand_hit = rand.nextInt(100);
                        if (rand_hit < 30) {
                            System.out.println("Slag "+(i+1)+" missar");
                        }
                        else{
                            System.out.println("Slag"+(i+1)+" Träffar");
                            dmgTot+=10;
                        }
    
                    }
                    System.out.println("Mågvert gjorde totalt "+dmgTot+" skada");
                    playerHP-=dmgTot;
                    break;
            }
        }
    }
    static int attack(int attackType, boolean potion){
        // Hämta line från array och skriv ut
        // Slumpa
        // Utvärdera träff/miss utifrån attackType
        // Beräkna skada utifrån attackType och potion
        // Hämta resultat-text från array
        // Returnera skada
        return 1;
    }

}
