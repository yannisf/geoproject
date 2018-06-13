package gr.fraglab.geoproject.persistence;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class AlternateName {

    private String name;
    @Enumerated(EnumType.STRING)
    private Character.UnicodeScript script;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Character.UnicodeScript getScript() {
        return script;
    }

    public void setScript(Character.UnicodeScript script) {
        this.script = script;
    }

}
