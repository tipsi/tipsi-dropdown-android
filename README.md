# TipsiDropdown

[![CI Status](https://img.shields.io/travis/tipsi/tipsi-dropdown-ios.svg?style=flat)](https://travis-ci.org/tipsi/tipsi-dropdown-android)
[![](https://jitpack.io/v/tipsi/tipsi-dropdown-android.svg)](https://jitpack.io/#tipsi/tipsi-dropdown-android)

## Usage
Add it in your root build.gradle at the end of repositories:
```ruby
buildscript {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```
Add the dependency:

```ruby
compile 'com.github.tipsi:tipsi-dropdown-android:0.4'
```

## Customization
In near future dropdown will support customization using the following code:

```java
Style style = new Style.Builder()
                  .withBackgroundColor("0x0000FF")
                  .withBorderWidth(2)
                  .withBorderColor("0xFFFFFF")
                  .withCornerRadius(10)
                  .withSeparatorHeight(1)
                  .withSeparatorColor("0xAAAAAA")
                  .withFontSize(15)
                  .withTextColor("0xCCCCCC")
                  .withTextAlignment("Left")
                  .withIndicatorImageName("custom_triangle") // image should be located in drawable
                  .withIndicatorImageResId(R.drawable.custom_triangle) // or via resource id
                  .build();
```

Adding dropdown into layout:
```xml
<com.gettipsi.tpsdropdown.DropdownContainer
        android:id="@+id/dropdown"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>
```

Applying style to dropdown
```java
((DropdownContainer) findViewById(R.id.dropdown)).setStyle(style);
```

You can also extend default class Adapter and set it with

```java
((DropdownContainer) findViewById(R.id.dropdown)).setupWithAdapter(customAdapter);
```

or init with list of elements
```java
List<String> items = Arrays.asList("One", "Two", "Three", "Four");
((DropdownContainer) findViewById(R.id.dropdown)).setupWithAdapter(items);
```

## Author

Tipsi team

## License

TipsiDropdown is available under the MIT license. See the LICENSE file for more info.
