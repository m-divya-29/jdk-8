package org.projects.lambda.functionalInterfaces;

import java.io.BufferedReader;
import java.io.IOException;

@FunctionalInterface
public interface IBuffReaderProcessor {
    String process(BufferedReader b) throws IOException;
}
