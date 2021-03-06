package org.icij.extract.cli.options;

import org.icij.extract.core.*;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.CommandLine;

/**
 * Extract
 *
 * @author Matthew Caruana Galizia <mcaruana@icij.org>
 * @version 1.0.0-beta
 * @since 1.0.0-beta
 */
public class ScannerOptionSet extends OptionSet {

	public ScannerOptionSet() {
		super(Option.builder()
				.desc("Glob pattern for matching files e.g. \"**/*.{tif,pdf}\". Files not matching the pattern will be ignored.")
				.longOpt("include-pattern")
				.hasArg()
				.argName("pattern")
				.build(),

			Option.builder()
				.desc("Glob pattern for excluding files and directories. Files and directories matching the pattern will be ignored.")
				.longOpt("exclude-pattern")
				.hasArg()
				.argName("pattern")
				.build(),

			Option.builder()
				.desc("Follow symbolic links when scanning for documents. Links are not followed by default.")
				.longOpt("follow-symlinks")
				.build(),

			Option.builder()
				.desc("Ignore hidden files. On DOS file systems, this means all files or directories with the \"hidden\" file attribute. On all other file systems, this means all file or directories starting with a dot. Hidden files are not ignored by default.")
				.longOpt("exclude-hidden-files")
				.build(),

			Option.builder()
				.desc("Include files and directories generated by common operating systems. This includes \"Thumbs.db\" and \".DS_Store\". The list is not determined by the current operating system. OS-generated files are ignored by default.")
				.longOpt("include-os-files")
				.build(),

			Option.builder()
				.desc("This is useful if your mount path for files varies from system to another, or if you simply want to hide the base of a path. For example, if you're working with a path that looks like \"/home/user/data\", specify \"/home/user/\" as the value for this option so that all queued paths start with \"data/\".")
				.longOpt("path-base")
				.hasArg()
				.argName("path")
				.build());
	}

	public static void configureScanner(final CommandLine cmd, final Scanner scanner) {
		if (cmd.hasOption("include-pattern")) {
			for (String includePattern : cmd.getOptionValues("include-pattern")) {
				scanner.addIncludeGlob(includePattern);
			}
		}

		if (cmd.hasOption("exclude-pattern")) {
			for (String excludePattern : cmd.getOptionValues("exclude-pattern")) {
				scanner.addExcludeGlob(excludePattern);
			}
		}

		scanner.ignoreSystemFiles(!cmd.hasOption("include-os-files"));
		scanner.ignoreHiddenFiles(cmd.hasOption("exclude-hidden-files"));
		scanner.followSymLinks(cmd.hasOption("follow-symlinks"));
	}
}
