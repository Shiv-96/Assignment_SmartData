package com.smartdatasolutions.test.impl;

import com.smartdatasolutions.test.Member;
import com.smartdatasolutions.test.MemberExporter;
import com.smartdatasolutions.test.MemberFileConverter;
import com.smartdatasolutions.test.MemberImporter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main extends MemberFileConverter {

	@Override
	protected MemberExporter getMemberExporter( ) {
		return new MemberExporterImpl();
	}

	@Override
	protected MemberImporter getMemberImporter( ) {
		return new MemberImporterImpl();
	}

	@Override
	protected List< Member > getNonDuplicateMembers( List< Member > membersFromFile ) {
		return new ArrayList<>(new HashSet<>(membersFromFile));
	}

	@Override
	protected Map< String, List< Member >> splitMembersByState( List< Member > validMembers ) {
		return validMembers.stream()
				.collect(Collectors.groupingBy(Member::getState));
	}

	public static void main( String[] args ) {
		//TODO
		File inputFile = new File("Members.txt");

		String outputFilePath = "output";

		String outputFileName = "outputFile.csv";

		Main converter = new Main();

		File outputDir = new File(outputFilePath);

		if(!outputDir.exists()){
			outputDir.mkdirs();
		}
		try {
			converter.convert(inputFile, outputFilePath, outputFileName);
			System.out.println(inputFile+" is split by state please check output folder");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

}
