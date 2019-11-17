package es.urjccode.mastercloudapps.adcs.draughts.models;

public class Turn {

    private Color color;

    public Turn(){
        this.color = Color.WHITE;
    }

    public Turn(Color color){
        this.color = color;
    }

    void change(){
        this.color = Color.values()[(this.color.ordinal()+1)%2];
    }

    public Color getColor() {
		return this.color;
    }
    
    @Override
    public String toString(){
        return this.color.name();
    }

	public boolean isColor(Color color2) {
		return false;
	}
}