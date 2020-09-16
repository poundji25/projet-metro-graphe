FX_LINUX_LIB=	lib/linux/lib
FX_LINUX_BIN=	lib/linux/bin
FX_WIN_LIB	=	lib/windows/lib
FX_WIN_BIN	=	lib/windows/bin
CONTROLSFX 	=	lib/controlsfx.jar
MODULES 	=	javafx.controls,javafx.fxml,javafx.graphics,controlsfx
JAVA 		=	java
JAVAC 		=	javac

jar: compile
	find $(FX_LINUX_LIB)/*.jar -exec unzip -nq {} -d out \;
	find $(FX_WIN_LIB)/*.jar -exec unzip -nq {} -d out \;
	find $(CONTROLSFX) -exec unzip -nq {} -d out \;
	rm -rf out/META-INF
	cp $(FX_LINUX_BIN)/* out
	cp $(FX_WIN_BIN)/* out
	rm -rf out/META-INF/MANIFEST.MF out/module-info.class
	mkdir jar
	jar --create --file=jar/MetroShoretesPath.jar --main-class=application.Launcher -C out .
	$(JAVA) -jar jar/MetroShoretesPath.jar

compile: clean
	mkdir out
	$(JAVAC) --module-path $(FX_WIN_LIB) --add-modules=$(MODULES) -d out $$(find src -name "*.java")
	$(JAVAC) --module-path $(FX_LINUX_LIB) --add-modules=$(MODULES) -d out $$(find src -name "*.java")
	cp -r src/application/css out/application
	cp -r src/application/resources out/application/
	cp -r src/application/view/sample.fxml out/application/view/sample.fxml


ZIPNAME=Version2_GUI
zip: clean
	@mkdir -p $(ZIPNAME)
	@cp -r src $(ZIPNAME)
	@cp -r lib $(ZIPNAME)
	@cp Makefile $(ZIPNAME)
	@cp README.md $(ZIPNAME)
	@zip -r $(ZIPNAME).zip $(ZIPNAME)
	@rm -rf $(ZIPNAME)

clean:
	rm -rf out
	rm -rf jar