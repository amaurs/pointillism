
package pointillism;

import java.util.List;
import java.io.File;

public abstract class AbstractParser implements IParser
{
	protected String file;


	public AbstractParser(String file)
	{
		this.file = file;
	}


	// public abstract List parse();
	public String toString()
	{
		return file;
	}
}
