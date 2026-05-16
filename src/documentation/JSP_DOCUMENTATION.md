# JSP Technical Documentation

This document describes the Java Server Pages (JSP) used in the Australian Toilet Finder project. The application uses Jakarta Server Pages (JSP) with JSTL for the web frontend, styled with Bootstrap 5.

## Overview

The JSPs are located in `src/main/webapp/WEB-INF/views/toilets/`. They provide the user interface for listing toilets, viewing specific features, and reading detailed notes.

## Common Features

- **Styling:** Bootstrap 5.3.3 via CDN.
- **Tag Libraries:** JSTL Core (`c:`) is used for loops, conditionals, and URL generation.
- **Responsiveness:** Use of Bootstrap's grid system, `.container-fluid`, and `.table-responsive`.
- **Navigation:** Buttons and links often preserve pagination state (`page`, `size`) and search context (`lat`, `lon`) using `<c:url>` and `<c:param>`.

---

## 1. Toilet List (`list.jsp`)

The main entry point for viewing toilets. It displays a paginated table of toilets and provides a search trigger.

- **URL Patterns:** `/`, `/listToilets`, `/nearby`
- **Controller Method:** `ToiletController.listToilets`, `ToiletController.findNearby`
- **Key Components:**
    - **Header Actions:**
        - **Find Nearby:** Triggers a browser geolocation request to find toilets near the user.
        - **Logout:** Standard Spring Security logout form.
    - **Toilet Table:** Displays `Actions`, `url`, `name`, `address`, `town`, and `state`.
        - **Truncation:** Long text in `url`, `name`, and `address` columns is truncated with ellipsis using a custom `.truncate` CSS class.
    - **Row Actions:**
        - **Features:** Links to the features checklist for the specific toilet.
        - **Notes:** Links to detailed notes for the specific toilet.
    - **Pagination:** A custom pagination component that shows a window of pages around the current page.

---

## 2. Toilet Features (`features.jsp`)

Displays a detailed checklist of features for a specific toilet (e.g., accessibility, baby change, etc.).

- **URL Pattern:** `/{id}/features`
- **Controller Method:** `ToiletController.toiletFeatures`
- **Key Components:**
    - **Selection Logic:** The controller selects the "best" feature record (preferring active records with no end date, then by latest start date).
    - **Feature Checklist:** A table listing various boolean features (e.g., Accessible, Male, Female, Baby Change).
        - **Status Badges:** Uses Bootstrap badges:
            - <span class="badge text-bg-success">Yes</span> (Success) for `true`.
            - <span class="badge text-bg-secondary">No</span> (Secondary) for `false`.
            - <span class="badge text-bg-warning">Unknown</span> (Warning) for `null`.
    - **Navigation:** "Back to list" button that preserves the previous list state (page, coordinates).

---

## 3. Toilet Notes (`toiletnotes.jsp`)

Displays descriptive text notes associated with a toilet.

- **URL Pattern:** `/{id}/notes`
- **Controller Method:** `ToiletController.toiletNotes`
- **Key Components:**
    - **Description List:** Uses Bootstrap's `<dl>`, `<dt>`, and `<dd>` to display various notes:
        - `addressNote`, `parkingNote`, `accessNote`, `openingHours`, `toiletNote`, etc.
    - **Note Rendering:** Uses `white-space: pre-wrap` for the `.note-content` class to preserve formatting of notes.
    - **Navigation:** "Back to List" button that preserves list state.

---

## Technical Details

### Page Context and Data
The JSPs expect the following objects in the `Model` (provided by `ToiletController`):

| Page | Model Attribute | Type | Description |
| :--- | :--- | :--- | :--- |
| `list.jsp` | `toiletPage` | `Page<Toilet>` | Spring Data Page of toilets. |
| `list.jsp` | `lat`, `lon` | `Double` | (Optional) Search coordinates. |
| `features.jsp`| `toilet` | `Toilet` | The toilet entity. |
| `features.jsp`| `feature` | `Feature` | The selected feature record. |
| `toiletnotes.jsp`| `toilet` | `Toilet` | The toilet entity. |

### CSS Customizations
Located within `<style>` blocks in the JSPs:
- `.truncate`: Limits width to `260px` and adds `text-overflow: ellipsis`.
- `.wrap`: Allows text wrapping for specific cells.
- `.note-label`: Fixed width for note titles in `toiletnotes.jsp`.
- `.note-content`: Preserves whitespace and wraps text.
