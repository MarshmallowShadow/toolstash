## 프로젝트 업데이트

### Requirements

gradle.properties

```
shouldPublish=true
```

### Version Controle

- Using Semantic Versioning Method: https://semver.org/lang/ko/

### Creating a New Release (Publishing)

1. Check `shouldPublish=true` property of deployment module for `gradle.properties`
2. https://github.com/vitasoftGit/VitaPack-Java
3. Releases > Draft a new release
4. Set tag to Project Version
5. Write Description > Publish Release
6. Module will be auto-published through `Actions`

## TODO After Publishing
1. Edit `gradle.properties`

```
shouldPublish=false
```

2. Update `UsageGuide.md` and other related documents
