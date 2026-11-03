/*
 * @(#)Settings.java  2.0.01  2019-02-02
 *
 * Copyright (c) 2019 Jerry Reno
 * This is public domain software, under the terms of the UNLICENSE
 * http://unlicense.org 
 *
 */

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/** Simple file-overridable resources */
public class Settings {
	private final String fn;
	private final File homefile;
	private final Properties builtin;
	private final Properties override;

	/** Load all of the properties in src/main/resources/filename
	 * and the file ~/filename
	 */
	public Settings(String filename)
	{
		fn = filename;
		builtin = new Properties();
		override = new Properties();
		String home = System.getProperty("user.home");
		homefile = new File(home, fn);
	}

	public void load()
	{
		builtin.clear();
		InputStream in = null;
		try
		{
			in = Settings.class.getResourceAsStream(fn);
			if (in != null) {
				builtin.load(in);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeQuietly(in);
		}

		loadOverride();
	}

	public void loadOverride()
	{
		override.clear();
		if (homefile == null || !homefile.exists()) return;
		InputStream in = null;
		try
		{
			in = new FileInputStream(homefile);
			override.load(in);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeQuietly(in);
		}
	}

	public String getString(String key)
	{
		return getString(key, null);
	}

	public String getString(String key, String def)
	{
		Object value = resolveValue(override, key);
		if (value != null) return value.toString();
		value = resolveValue(builtin, key);
		if (value != null) return value.toString();
		return def;
	}

	public Boolean getBoolean(String key)
	{
		return getBoolean(key, null);
	}

	public Boolean getBoolean(String key, Boolean def)
	{
		Object value = resolveValue(override, key);
		if (value != null) {
			Boolean parsed = parseBoolean(value);
			if (parsed != null) return parsed;
		}
		value = resolveValue(builtin, key);
		if (value != null) {
			Boolean parsed = parseBoolean(value);
			if (parsed != null) return parsed;
		}
		return def;
	}

	public Integer getInt(String key)
	{
		return getInt(key, null);
	}

	public Integer getInt(String key, Integer def)
	{
		Object value = resolveValue(override, key);
		if (value != null) {
			Integer parsed = parseInteger(value);
			if (parsed != null) {
				override.put(key, parsed);
				return parsed;
			}
		}
		value = resolveValue(builtin, key);
		if (value != null) {
			Integer parsed = parseInteger(value);
			if (parsed != null) {
				override.put(key, parsed);
				return parsed;
			}
		}
		return def;
	}

	public void setInt(String key, Integer value)
	{
		if (value == null) {
			override.remove(key);
			return;
		}
		override.put(key, value);
	}

	public void setString(String key, String value)
	{
		if (value == null) {
			override.remove(key);
			return;
		}
		override.put(key, value);
	}

	public void setBoolean(String key, Boolean value)
	{
		if (value == null) {
			override.remove(key);
			return;
		}
		override.put(key, value);
	}

	public void save()
	{
		if (homefile == null) return;
		File parent = homefile.getParentFile();
		if (parent != null && !parent.exists()) {
			parent.mkdirs();
		}
		OutputStream out = null;
		try {
			out = new FileOutputStream(homefile);
			override.store(out, "Kairo user settings");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			closeQuietly(out);
		}
	}

	private Object resolveValue(Properties source, String key) {
		return source.get(key);
	}

	private Boolean parseBoolean(Object value) {
		if (value instanceof Boolean) return (Boolean) value;
		try {
			return Boolean.valueOf(value.toString());
		}
		catch (Exception e) {
			return null;
		}
	}

	private Integer parseInteger(Object value) {
		if (value instanceof Integer) return (Integer) value;
		try {
			return Integer.valueOf(value.toString());
		}
		catch (NumberFormatException e) {
			return null;
		}
	}

	private void closeQuietly(Closeable closeable) {
		if (closeable == null) return;
		try {
			closeable.close();
		} catch (IOException e) { }
	}

}
