run: compile
	@java -cp out application.Main

compile: clean
	@mkdir out
	@javac -d out $$(find src -name "*.java")
	@cp -r src/application/resources out/application/resources

# ZIPNAME=ProjetMetroV1Console
ZIPNAME=Version1_Console
zip: clean
	@mkdir -p $(ZIPNAME)
	@cp -r src $(ZIPNAME)
	@cp Makefile $(ZIPNAME)
	@cp README.md $(ZIPNAME)
	@zip -r $(ZIPNAME).zip $(ZIPNAME)
	@rm -rf $(ZIPNAME)

clean:
	@rm -rf out