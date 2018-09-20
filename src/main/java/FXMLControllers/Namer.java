package FXMLControllers;

import Types.ExperimentManager;
import Types.KeywordManager;
import Utilities.Config;
import Utilities.KeywordAutocompleteTextField;
import com.jfoenix.controls.JFXTextField;

import javax.naming.NameNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;

import static FXMLControllers.FullNamer.sharedListOfKeywordStrings;

public class Namer extends ScreenController{

    String updateName(String experimentTypeText,
                      String trialNumberText,
                      String sampleNumberText,
                      String researcherNameText,
                      LocalDate experimentDate,
                      ArrayList<KeywordAutocompleteTextField> sharedListOfKeywords)
    {
        Config config = new Config();
        String delimiter = config.getProperty("delimiter");
        if(delimiter == null){
            delimiter = "_";
        }

        StringBuilder fname = new StringBuilder();

        if(experimentDate != null)
        {
            fname.append(experimentDate.getYear());
            fname.append(delimiter);
            fname.append(experimentDate.getMonthValue());
            fname.append(delimiter);
            fname.append(experimentDate.getDayOfMonth());
        }

        String experimentShorthand = "";

        if(experimentTypeText != null && !(experimentTypeText.trim().isEmpty()))
        {
            fname.append(delimiter);
            try {
                experimentShorthand = ExperimentManager.getInstance().getExperimentByName("long",experimentTypeText).getShortName();
                fname.append(experimentShorthand);
            } catch (NameNotFoundException e1) {
                e1.printStackTrace();
            }
        }

        if(trialNumberText != null && !trialNumberText.trim().isEmpty())
        {
            fname.append(delimiter);
            fname.append("T");
            fname.append(trialNumberText);
        }

        if(sampleNumberText != null && !sampleNumberText.trim().isEmpty())
        {
            fname.append(delimiter);
            fname.append("S");
            fname.append(sampleNumberText);
        }

        StringBuilder initial = new StringBuilder();
        if(researcherNameText != null && researcherNameText.length() != 0)
        {
            String name = researcherNameText.toUpperCase();

            String[] parts = name.split(" ");
            StringBuilder finalInitial = new StringBuilder();

            String sepIni;

            for(int i=0; i<parts.length; i++) {

                sepIni = parts[i].substring(0,1);
                finalInitial = initial.append(sepIni);

            }

            fname.append(delimiter);
            fname.append(finalInitial);

        }
        int i = 0;
        for(KeywordAutocompleteTextField autocompleteTextField : sharedListOfKeywords)
        {
            if(sharedListOfKeywordStrings.get(i) != null && !sharedListOfKeywordStrings.get(i).trim().isEmpty())
            {
                String keyword;
                fname.append(delimiter);
                try {
                    JFXTextField keywordValue = autocompleteTextField.getKeywordValueField();
                    keyword = KeywordManager.getInstance().getKeywordByName("long",sharedListOfKeywordStrings.get(i)).getShortName();
                    if(true/*autocompleteTextField.getState() == 1*/)
                    {
                        String affix = KeywordManager.getInstance().getKeywordByName("long",sharedListOfKeywordStrings.get(i)).getAffix();
                        switch (affix){
                            case "prefix":
                                fname.append(keyword);
                                if(keywordValue.getText() != null && !keywordValue.getText().trim().isEmpty())
                                {
                                    fname.append(keywordValue.getText());
                                }
                                break;
                            case "suffix":
                                if(keywordValue.getText() != null && !keywordValue.getText().trim().isEmpty())
                                {
                                    fname.append(keywordValue.getText());
                                }
                                fname.append(keyword);
                                break;
                            case "none":
                                fname.append(keyword);
                                break;
                            default:
                                fname.append(keyword);
                                break;
                        }
                    }
                } catch (NameNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
            i++;
        }
        return fname.toString();
    }
}