package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage window) throws Exception {
        //vanaf hier kunt ge uw code beginnen typen om een javafx-applicatie te maken, al hetgeen hierboven is nodig om een GUI te maken
        //de "stage" is uw window, bovenste balk met kruisje, minimalize, maximize
        // de "scene" is al hetgene in uw 'stage', al uw elementen zoals knoppen, inputfields enzo

        //titel van uw nieuwe window of 'stage', deze titel verschijnt in de bovenste balk van de window
        window.setTitle("EA Tax Calculator - It's not in the game");

        //een denkbeeldig raster maken waar je dan elementen in kan zetten zodat ze gealigneerd zijn
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10, 10, 10, 10)); //zorgt dat uw elementen niet tegen de rand van uw window plakken
        layout.setVgap(8); //zorgt dat er plaats is verticaal tussen uw elementen
        layout.setHgap(10); //horizontaal

        //Labels + tekst inputfields maken
        Label labelAankoop = new Label("Aankoopprijs: ");
        GridPane.setConstraints(labelAankoop, 0, 1); //dit plaats labelAankoop in de eerste kolom en in de 2de rij
        TextField textfield = new TextField(); //een input veldje waar de user tekst in kan typen, zoals bij een login
        GridPane.setConstraints(textfield, 1, 1);

        Label labelVerkoop = new Label("Verkoopprijs: ");
        GridPane.setConstraints(labelVerkoop, 0, 2);
        TextField textfield2 = new TextField();
        GridPane.setConstraints(textfield2, 1, 2);

        //labels maken voor het resultaat (er komt pas tekst in via de method)
        Label labelResultaat = new Label();
        GridPane.setConstraints(labelResultaat, 1, 4);
        Label labelResultaat2 = new Label();
        GridPane.setConstraints(labelResultaat2, 1, 5);

        //nieuwe knop aanmaken en zet er tekst in
        Button knopje1 = new Button("Calculate tax");
        GridPane.setConstraints(knopje1, 1, 3); //zet knopje1 in de 2de kolom en op de 3de rij

        //functie toevoegen aan knopje bij klikken
        knopje1.setOnAction( e -> {

            try {
                //door de try-catch kan je exceptions opvangen
                //doe deze code, indien error: doe blokje code onder catch

                //omzetten van input user in ints, indien dit niet lukt (door een letter in te voeren)
                // gaat hij meteen naar de code onder catch
                int aankoop1 = Integer.parseInt(textfield.getText());
                int verkoop1 = Integer.parseInt(textfield2.getText());

                //deze roept de method aan en steekt, wat de method returned, in een variabele (deze moeten dezelfde datatypes hebben)
                int[] result = calculation(aankoop1, verkoop1);

                //Result tonen van de method, door de setText pas hij de tekst telkens opnieuw aan bij het klikken op de knop
                //in plaats van telkens een nieuw label aan te maken
                labelResultaat.setText("Winst: " + result[0]);
                labelResultaat2.setText("EA Tax: " + result[1] + " (dit al van de winst afgetrokken)");


            } catch (Exception a) {
                //als blokje code van try een exception/error geeft, doe deze code
                //verandert beide labels
                labelResultaat.setText("Gebruik een getal pls");
                labelResultaat.setTextFill(Color.web("#ff0000"));
                labelResultaat2.setText("");

            }

        }); //haakje van de button on action event, is tussen curly braces gezet zodat hij alles ertussen uitvoert

        //toont alle elementen in de scene/layout
        layout.getChildren().addAll(knopje1, labelAankoop, textfield, labelVerkoop, textfield2, labelResultaat, labelResultaat2);

        //maak de scene/layout + toon window inclusief de scene
        Scene scene = new Scene(layout, 400, 200); //instantieer de 'scene', gebruik "layout" als layout, en geef de size van de window
        window.setScene(scene);
        window.show();
    }

    //de method van de eatax + winst berekening, geeft een array van twee waarden terug, 0 = winst && 1 = EATax
    private int[] calculation(int aankoop, int verkoop) {

        //Berekenen
        int eaTax = (verkoop / 100) * 5;
        int winst = (verkoop - eaTax) - aankoop;

        //return winst + EATax
        return new int[] {winst, eaTax};
    }

}

