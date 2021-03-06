import com.google.common.base.Objects;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Exceptions;

@SuppressWarnings("all")
public class test {
  public static Set<String> filtervenues = new HashSet<String>();
  
  public static void main(final String[] args) {
    try {
      final test instance = new test();
      FileReader _fileReader = new FileReader("C:\\Users\\Suganya\\Downloads\\dblp.v11\\venuesexpansion.txt");
      BufferedReader br = new BufferedReader(_fileReader);
      String sCurrentLine = "";
      String filtered = "";
      while ((!Objects.equal((sCurrentLine = br.readLine()), null))) {
        {
          filtered = test.getOnlyStrings(sCurrentLine);
          test.filtervenues.add(filtered.toLowerCase());
        }
      }
      List<PapersPojo> papers = new ArrayList<PapersPojo>();
      Set<Integer> years = new HashSet<Integer>();
      boolean core = true;
      String datasetname = "FinalDataset.txt";
      PrintWriter writer = new PrintWriter("src\\Papers\\Authors.msl", "UTF-8");
      papers = JsonParse.extractAuthors(Boolean.valueOf(core), datasetname, test.filtervenues);
      datasetname = "References_dataset_new.txt";
      core = false;
      papers = JsonParse.extractAuthors(Boolean.valueOf(false), datasetname, test.filtervenues);
      writer.println(instance.generateAuthorsMSL(papers));
      writer.close();
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public String generatePapersMSL(final List<PapersPojo> papers, final Integer year) {
    String _xblockexpression = null;
    {
      int i = 0;
      boolean flag = true;
      Set<Integer> years = new HashSet<Integer>();
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("import \"platform:/resource/SLROnToleranceInMDE/src/Language.msl\"");
      _builder.newLine();
      _builder.append("import \"platform:/resource/SLROnToleranceInMDE/src/Papers/Authors.msl\"");
      _builder.newLine();
      _builder.append("import \"platform:/resource/SLROnToleranceInMDE/src/Papers/Venues.msl\"");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("�FOR paper : papers�");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("�IF(paper.getYear !== year) && !years.contains(paper.getYear)�\t");
      _builder.newLine();
      _builder.append("\t\t\t      ");
      _builder.append("�{years.add(paper.getYear); \"\" }�");
      _builder.newLine();
      _builder.append("import \"platform:/resource/SLROnToleranceInMDE/src/Papers/Paper�paper.getYear.toString()�.msl\"");
      _builder.newLine();
      _builder.append("          ");
      _builder.append("�ENDIF�          ");
      _builder.newLine();
      _builder.append(" \t");
      _builder.append("�ENDFOR�");
      _builder.newLine();
      _builder.append(" \t");
      _builder.newLine();
      _builder.append("model Paper�year.toString()�{");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("�FOR paper : papers�");
      _builder.newLine();
      _builder.append("\t      \t");
      _builder.append("�IF (paper.getYear()==year)�");
      _builder.newLine();
      _builder.append("\t      \t    ");
      _builder.append("paper�paper.getId()�:Paper {");
      _builder.newLine();
      _builder.append("                ");
      _builder.append(".title : \"�getOnlyStrings(paper.getTitle())�\"");
      _builder.newLine();
      _builder.append("            \t");
      _builder.append(".year : �paper.getYear()�");
      _builder.newLine();
      _builder.append("            \t");
      _builder.append(".core : �paper.getCore()�");
      _builder.newLine();
      _builder.append("            \t");
      _builder.append(".relevance:�paper.getRelevance()�");
      _builder.newLine();
      _builder.append("            \t");
      _builder.append("-venue->venue�paper.getVenue()�");
      _builder.newLine();
      _builder.append("            \t ");
      _builder.append("�var pool=PapersPojo.poolids�");
      _builder.newLine();
      _builder.append("            \t");
      _builder.append("�FOR reference : paper.getReferences()�");
      _builder.newLine();
      _builder.append("            \t     ");
      _builder.append("�IF (pool.contains(reference))�");
      _builder.newLine();
      _builder.append("            \t      ");
      _builder.append("-cites->paper�reference�");
      _builder.newLine();
      _builder.append("            \t     ");
      _builder.append("�ENDIF�");
      _builder.newLine();
      _builder.append("                ");
      _builder.append("�ENDFOR�");
      _builder.newLine();
      _builder.append("            \t");
      _builder.append("�FOR author : paper.getAuthors()�");
      _builder.newLine();
      _builder.append("            \t       ");
      _builder.append("-authors->author�author�");
      _builder.newLine();
      _builder.append("            \t ");
      _builder.append("�ENDFOR�");
      _builder.newLine();
      _builder.append("            ");
      _builder.append("}");
      _builder.newLine();
      _builder.append("                     ");
      _builder.append("�ENDIF�");
      _builder.newLine();
      _builder.append("                ");
      _builder.append("�ENDFOR�");
      _builder.newLine();
      _builder.append("���    \t\t\t�var s=PapersPojo.dummypaperids�");
      _builder.newLine();
      _builder.append("���    \t\t\t\t�FOR a : s�");
      _builder.newLine();
      _builder.append("���    \t\t\t\t paper�a�:Paper {");
      _builder.newLine();
      _builder.append("���    \t\t\t\t \t.title : \"\"");
      _builder.newLine();
      _builder.append("���    \t\t\t\t    .year : 0000");
      _builder.newLine();
      _builder.append("���    \t\t\t\t    .core : false");
      _builder.newLine();
      _builder.append("���    \t\t\t\t    .venue : \"\"    \t\t\t\t           ");
      _builder.newLine();
      _builder.append("���    \t\t\t\t            }");
      _builder.newLine();
      _builder.append("���    \t\t\t\t    �ENDFOR�   ");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _xblockexpression = _builder.toString();
    }
    return _xblockexpression;
  }
  
  public String generateAuthorsMSL(final List<PapersPojo> papers) {
    String _xblockexpression = null;
    {
      int i = 1;
      String fname = "";
      String lname = "";
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("import \"platform:/resource/SLROnToleranceInMDE/src/Language.msl\"");
      _builder.newLine();
      _builder.newLine();
      _builder.append("model AllAuthors {");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("�var s=PapersPojo.id_to_authors_global�");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("�FOR a : s.entrySet�    ");
      _builder.newLine();
      _builder.append("\t        ");
      _builder.append("�{fname=getOnlyStrings(a.value.split(\"\\\\s\").get(0));\"\"}�  ");
      _builder.newLine();
      _builder.append("\t        ");
      _builder.append("�IF (a.value.split(\"\\\\s\").size>=2)�");
      _builder.newLine();
      _builder.append("\t        ");
      _builder.append("�{lname=getOnlyStrings(a.value.split(\"\\\\s\").get(1));\"\"}�");
      _builder.newLine();
      _builder.append("\t        ");
      _builder.append("�ELSE�\t        ");
      _builder.newLine();
      _builder.append("\t        ");
      _builder.append("�lname=\"\"�  ");
      _builder.newLine();
      _builder.append("\t        ");
      _builder.append("�ENDIF�");
      _builder.newLine();
      _builder.append("\t             ");
      _builder.append("author�a.key�:Author {");
      _builder.newLine();
      _builder.append("            \t");
      _builder.append(".firstName : \"�fname�\"");
      _builder.newLine();
      _builder.append("            \t");
      _builder.append(".lastName : \"�lname�\"    \t");
      _builder.newLine();
      _builder.append("            ");
      _builder.append("}");
      _builder.newLine();
      _builder.append("            ");
      _builder.append("�ENDFOR�");
      _builder.newLine();
      _builder.append("    ");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _xblockexpression = _builder.toString();
    }
    return _xblockexpression;
  }
  
  public String generateVenuesMSL(final List<PapersPojo> papers) {
    String _xblockexpression = null;
    {
      int i = 1;
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("import \"platform:/resource/SLROnToleranceInMDE/src/Language.msl\"");
      _builder.newLine();
      _builder.newLine();
      _builder.append("model AllVenues {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("�var v=PapersPojo.venues_global�\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("�var Set<String> venues = new HashSet�");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("�FOR paper : papers�");
      _builder.newLine();
      _builder.append("\t ");
      _builder.append("�IF (!venues.contains(paper.getVenuename().toLowerCase))�");
      _builder.newLine();
      _builder.append("\t       ");
      _builder.append("venue�paper.getVenue�:Venue {");
      _builder.newLine();
      _builder.append("\t             \t");
      _builder.append(".Name : \"�paper.getVenuename�\"");
      _builder.newLine();
      _builder.append("\t             \t");
      _builder.append(".SE: �paper.getSE�");
      _builder.newLine();
      _builder.append("\t             ");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t              ");
      _builder.append("�{venues.add(paper.getVenuename().toLowerCase); \"\" }�");
      _builder.newLine();
      _builder.append("\t  \t\t\t\t        ");
      _builder.append("�ENDIF�");
      _builder.newLine();
      _builder.append("            ");
      _builder.append("�ENDFOR�");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _xblockexpression = _builder.toString();
    }
    return _xblockexpression;
  }
  
  public static String getOnlyStrings(final String s) {
    Pattern pattern = Pattern.compile("[^a-z A-Z\\s]");
    Matcher matcher = pattern.matcher(s);
    String name = matcher.replaceAll("");
    return name;
  }
}
