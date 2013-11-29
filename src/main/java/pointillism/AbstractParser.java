package pointillism;

import java.util.List;
import java.io.File;
public abstract class AbstractParser implements IParser{
	protected File file;
	public AbstractParser(String file){
		this.file=new File(file);
	}	
	//public abstract List parse();
	public String toString(){
		return file.getName();
	}
}
