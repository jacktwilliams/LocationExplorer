See the DB directory for database initialization instructions.

If Eclipse gives you an error, "Cannot change version of project facet"
Then open .settings/org.eclipse.wst.common.project.facet.core.xml and change jst.web verison to 3.0. Then update maven project in eclipse.
Also make sure project has server selected as a targeted runtime. (Properties -> Targeted Runtimes)
