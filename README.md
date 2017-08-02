# cf-java [![Build Status](https://travis-ci.org/joergen7/cf-java.svg?branch=master)](https://travis-ci.org/joergen7/cf-java)

###### Java bindings for Cuneiform

This Java library encapsulates all communication with a Cuneiform interpreter service as implemented by [cf_lang](https://github.com/joergen7/cf_lang). cf-java is used to send a workflow to the interpreter, receive workflow task invocations which can be executed using [effi](https://github.com/joergen7/effi), to ship back the invocation result to the interpreter service, and to receive the workflow result once all task invocations have been processed.

The communication protocols must be expected to change over time. Thus, cf-java 0.0.3-RELEASE is compatible with cf_lang 0.1.0 and effi 0.1.3. No other version combination of these tools can be expected to work.

## Usage

### Adding cf-java to a Maven project

The following dependencies and repositories need to be added to your `pom.xml` to add the cf-java library to the dependencies of a given Maven project:

    <dependencies>
        <dependency>
            <groupId>de.hu-berlin.wbi</groupId>
            <artifactId>cf-java</artifactId>
            <version>0.0.3-RELEASE</version>
        </dependency>
    </dependencies>

    <repositories>
        <repository>
            <id>ossrh</id>
            <url>https://oss.sonatype.org/content/groups/public</url>
        </repository>
    </repositories>


## Resources

- [cf_lang](https://github.com/joergen7/cf_lang). An Erlang-based web service implementing a Cuneiform interpreter.
- [effi](https://github.com/joergen7/effi). A command-line tool for executing single Cuneiform invocations.

## Authors

- Jörgen Brandt (joergen7) [joergen.brandt@onlinehome.de](mailto:joergen.brandt@onlinehome.de)

## License

[Apache 2.0](https://www.apache.org/licenses/LICENSE-2.0.html)
